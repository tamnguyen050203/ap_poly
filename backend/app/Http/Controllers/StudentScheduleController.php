<?php

namespace App\Http\Controllers;

use App\Models\StudentSchedule;
use App\Http\Requests\StoreStudentScheduleRequest;
use App\Http\Requests\UpdateStudentScheduleRequest;

class StudentScheduleController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \App\Http\Requests\StoreStudentScheduleRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreStudentScheduleRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\StudentSchedule  $studentSchedule
     * @return \Illuminate\Http\Response
     */
    public function show(StudentSchedule $studentSchedule)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\StudentSchedule  $studentSchedule
     * @return \Illuminate\Http\Response
     */
    public function edit(StudentSchedule $studentSchedule)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateStudentScheduleRequest  $request
     * @param  \App\Models\StudentSchedule  $studentSchedule
     * @return \Illuminate\Http\Response
     */
    public function update(UpdateStudentScheduleRequest $request, StudentSchedule $studentSchedule)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\StudentSchedule  $studentSchedule
     * @return \Illuminate\Http\Response
     */
    public function destroy(StudentSchedule $studentSchedule)
    {
        //
    }
}
