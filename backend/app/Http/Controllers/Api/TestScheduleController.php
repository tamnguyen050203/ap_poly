<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\TestSchedule;
use Illuminate\Http\Client\Request;

class TestScheduleController extends Controller
{
    public function __contruct()
    {
        $this->model = (new TestSchedule::class)->query();
    }

    public function getTestSchedules(Request $request)
    {
        
    }
}
