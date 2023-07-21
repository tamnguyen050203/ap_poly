<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\StudentClass;
use App\Models\StudentTestSchedule;
use App\Models\TestSchedule;
use Illuminate\Http\Request;

class TestScheduleController extends Controller
{
    public function __construct()
    {
        $this->model = (new TestSchedule)->query();
    }

    public function getTestSchedules(Request $request)
    {
        $studentTestSchedule = StudentTestSchedule::query()
            ->where('student_id', auth()->user()->id);
        $test_schedule_id = $studentTestSchedule->pluck('test_schedule_id');
        $is_attend = $studentTestSchedule->pluck('is_attend');

        // Find class group name
        $class_group_id = StudentClass::
        where('student_id', '=', auth()->user()->id)
            ->first();
        $class_group_name = $class_group_id ? $class_group_id->class_group->name : null;

        $testSchedule = $this->model
            ->when($request->date, function ($query) use ($request) {
                return $query->where('date', $request->date);
            })
            ->join('shifts', 'shifts.id', '=', 'test_schedules.shift_id')
            ->join('rooms', 'rooms.id', '=', 'test_schedules.room_id')
            ->join('lessons', 'lessons.id', '=', 'test_schedules.lesson_id')
            ->join('amphitheaters', 'amphitheaters.id', '=', 'rooms.amphitheater_id')
            ->whereIn('test_schedules.id', $test_schedule_id)
            ->select(
                'test_schedules.id',
                'lessons.name as lesson_name',
                'rooms.name as room_name',
                'test_schedules.date',
                'shifts.name as shift_name',
                'shifts.start_time',
                'shifts.end_time',
                'amphitheaters.name as amphitheater_name',
            )
            ->latest('date')
            ->orderBy('shifts.start_time')
            ->paginate(5);

        $testSchedule->getCollection()->transform(function ($item, $key) use ($is_attend, $class_group_name) {
            $item->is_attend = !!$is_attend[$key];
            $item->class_group_name = $class_group_name;
            return $item;
        });

        return response()->json([
            'studentTestSchedule' => $testSchedule,
        ], 200);
    }
}
