<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\LecturerTestSchedule;
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

        $testSchedules = $this->model
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
                'lessons.code_name as lesson_code_name',
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

        // Check student attend test
        foreach ($testSchedules as $item) {
            $isAttended = StudentTestSchedule::query()
                ->where('test_schedule_id', $item->id)
                ->where('student_id', auth()->user()->id)
                ->select('is_attend')
                ->first();

            $item->is_attend = (bool) $isAttended['is_attend'] ?? '';
        }

        // Get id lecturer in lecturer_test_schedule
        foreach ($testSchedules as $item) {
            $lecturer_name = LecturerTestSchedule::query()
                ->where('test_schedule_id', $item->id)
                ->join('lecturers', 'lecturers.id', '=', 'lecturer_test_schedules.lecturer_id')
                ->select('lecturers.name as lecturer_name')
                ->first();

            $item->lecturer_name = $lecturer_name['lecturer_name'] ?? '';
        }

        //        dd($testSchedules);

        return response()->json([
            'status' => 200,
            'schedules' => $testSchedules,
        ], 200);
    }
}
