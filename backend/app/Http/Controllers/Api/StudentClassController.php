<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\StoreStudentClassRequest;
use App\Http\Requests\UpdateStudentClassRequest;
use App\Models\StudentClass;

class StudentClassController extends Controller
{
    public function index()
    {
        return StudentClass::find()->students()->get();
    }

    public function create()
    {
        //
    }

    public function store(StoreStudentClassRequest $request)
    {
        //
    }

    public function show(StudentClass $studentClass)
    {
        //
    }

    public function edit(StudentClass $studentClass)
    {
        //
    }

    public function update(UpdateStudentClassRequest $request, StudentClass $studentClass)
    {
        //
    }

    public function destroy(StudentClass $studentClass)
    {
        //
    }
}
