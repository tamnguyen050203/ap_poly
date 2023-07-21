<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Student;
use Illuminate\Http\JsonResponse;

class StudentController extends Controller
{

    public function __construct()
    {
        $this->model = (new Student())->query();
    }

    public function getInfo(): JsonResponse
    {
        $student = auth()->user()
            ->only(
                'id',
                'name',
                'email',
                'avatar',
                'provider_id',
                'dob',
                'phone',
            );

        $student['specialize'] = auth()->user()->specialize->name;

        return response()->json([
            'status' => 2000,
            'student' => $student,
        ]);
    }
}
