<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Schedule;
use App\Models\StudentClass;
use App\Models\StudentSchedule;
use Illuminate\Http\Request;

class ScheduleController extends Controller
{

    public function __construct()
    {
        $this->model = (new Schedule())->query();
    }

    public function getSchedules(Request $request)
    {
        $class_group_id = StudentClass::
        where('student_id', '=', auth()->user()->id)
            ->first()->class_group_id;

        if (!$class_group_id) {
            return response()->json([
                'message' => 'Không tìm thấy lớp học phù hợp',
            ], 404);
        }

        $schedules = $this->model
            ->when($request->dateStart, function ($query) use ($request) {
                return $query->where('date', '>=', $request->dateStart);
            })
            ->when($request->dateEnd, function ($query) use ($request) {
                return $query->where('date', '<=', $request->dateEnd);
            })
            ->when($request->date, function ($query) use ($request) {
                return $query->where('date', $request->date);
            })
            ->where('class_group_id', $class_group_id)
            ->join('shifts', 'shifts.id', '=', 'schedules.shift_id')
            ->join('rooms', 'rooms.id', '=', 'schedules.room_id')
            ->join('lessons', 'lessons.id', '=', 'schedules.lesson_id')
            ->join('class_groups', 'class_groups.id', '=', 'schedules.class_group_id')
            ->join('lecturers', 'lecturers.id', '=', 'class_groups.lecturer_id')
            ->join('amphitheaters', 'amphitheaters.id', '=', 'rooms.amphitheater_id')
            ->select(
                'schedules.id',
                'lessons.name as lesson_name',
                'lessons.code_name as lesson_code_name',
                'class_groups.link as class_group_link',
                'class_groups.name as class_group_name',
                'rooms.name as room_name',
                'schedules.date',
                'shifts.name as shift_name',
                'shifts.start_time',
                'shifts.end_time',
                'lecturers.name as lecturer_name',
                'amphitheaters.name as amphitheater_name',
                'schedules.detail',
            )
            ->latest('schedules.date')
            ->orderBy('shifts.start_time')
            ->paginate(5);

        return response()->json([
            'status' => 200,
            'schedules' => $schedules,
        ]);
    }

    public function setAlarmSchedule(Request $request)
    {
        $student_id = auth()->user()->id;
        $student_class_id = StudentClass::where('student_id', $student_id)->first()->id;

        StudentSchedule::query()
            ->where('student_class_id', $student_class_id)
            ->where('schedule_id', $request->schedule_id)
            ->update([
                'reminder_id' => $request->reminder_id,
                'is_alarm' => $request->is_alarm,
            ]);

        return response()->json([
            'status' => 200,
            'message' => 'Cập nhật thành công',
        ]);
    }
}
