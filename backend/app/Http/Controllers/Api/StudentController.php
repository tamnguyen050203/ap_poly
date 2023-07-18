<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Student;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;

class StudentController extends Controller
{
    public function getInfo(Request $request): JsonResponse
    {
        $bearerToken = hash('sha256', $request->bearerToken());
        $student = Student::where('access_token', $bearerToken)->first();
        return response()->json([
            'user' => $student,
        ]);
    }
}
