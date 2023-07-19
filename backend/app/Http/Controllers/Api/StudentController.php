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
        $bearerToken = hash('sha256', $request->bearerToken());
        $student = $this->model
            ->with(['specialize' => function ($query) {
                $query->select('id', 'name');
            }])
            ->where('access_token', $bearerToken)
            ->select('id', 'name', 'email', 'avatar', 'specialize_id')
            ->first();

        return response()->json([
            'user' => $student,
        ]);
    }
}
