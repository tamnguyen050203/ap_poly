<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Schedule;
use App\Models\StudentClass;
use Illuminate\Http\Request;

class ScheduleController extends Controller
{

    public function __construct()
    {
        $this->model = (new Schedule())->query();
    }

    public function getSchedules(Request $request)
    {
        $access_token = hash('sha256', $request->bearerToken());

        $class_group_id = StudentClass::
        whereHas('student', function ($query) use ($access_token) {
            $query->where('access_token', $access_token);
        })->first()->class_group_id;

        if (!$class_group_id) {
            return response()->json([
                'message' => 'Không tìm thấy lớp học phù hợp',
            ], 404);
        }

        $schedules = $this->model
            ->when($request->date, function ($query) use ($request) {
                return $query->where('date', $request->date);
            })
            ->whereHas('class_group', function ($query) use ($class_group_id) {
                $query->where('id', $class_group_id);
            })
            ->join('shifts', 'shifts.id', '=', 'schedules.shift_id')
            ->join('rooms', 'rooms.id', '=', 'schedules.room_id')
            ->join('lessons', 'lessons.id', '=', 'schedules.lesson_id')
            ->join('class_groups', 'class_groups.id', '=', 'schedules.class_group_id')
            ->join('lecturers', 'lecturers.id', '=', 'class_groups.lecturer_id')
            ->join('amphitheaters', 'amphitheaters.id', '=', 'rooms.amphitheater_id')
            ->select(
                'schedules.id',
                'lessons.name as lesson_name',
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
            'schedules' => $schedules,
        ]);
    }
}
