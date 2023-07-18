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

    public function index()
    {

    }

    public function getSchedules(Request $request)
    {
        $student_id = $request->student_id;
        $class_group_id = StudentClass::where(
            'student_id',
            $student_id
        )->first()->class_group_id;
        $schedules = $this->model->whereHas('classGroup', function ($query) use ($class_group_id) {
            $query->where('class_group_id', $class_group_id);
        })->with(['classGroup', 'lesson', 'room', 'shift'])->toSql();
        dd($schedules);
        return response()->json($schedules);
    }
}
