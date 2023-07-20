<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Student;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class StudentController extends Controller
{

    public function __construct()
    {
        $this->model = (new Student())->query();
    }

    public function getInfo(Request $request): JsonResponse
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
            'student' => $student,
        ]);
    }
}
