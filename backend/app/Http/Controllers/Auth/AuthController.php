<?php

namespace App\Http\Controllers\Auth;

use App\Helpers\RandomInfo;
use App\Models\ClassGroup;
use App\Models\Specialize;
use App\Models\Student;
use App\Models\StudentClass;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Routing\Controller;
use Illuminate\Support\Carbon;
use Illuminate\Support\Str;

class AuthController extends Controller
{
    public function __construct()
    {
    }

    public function login(Request $request): JsonResponse
    {
        //check if user exists
        $student = Student::where('email', $request->email,)
            ->where('provider_id', $request->provider_id)
            ->first();

        if (!$student) {

            if (!$request->name) {
                return response()->json([
                    'status' => 400,
                    'error' => 'Name is required'
                ], 400);
            }

            if (!$request->avatar) {
                return response()->json([
                    'status' => 400,
                    'error' => 'Avatar is required'
                ], 400);
            }

            if (!$request->provider_id) {
                return response()->json([
                    'status' => 400,
                    'error' => 'Provider is required'
                ], 400);
            }

            if (!$request->email) {
                return response()->json([
                    'status' => 400,
                    'error' => 'Email is required'
                ], 400);
            }

            $randomSpecialize = rand(1, Specialize::query()->count());
            $dob = RandomInfo::randomDateOfBirth(1995, 2005);
            $phone = RandomInfo::randomPhone();

            $student = Student::firstOrCreate(
                [
                    'email' => $request->email,
                    'name' => $request->name,
                    'avatar' => $request->avatar,
                    'dob' => $dob,
                    'phone' => $phone,
                    'provider_id' => $request->provider_id,
                    'specialize_id' => $randomSpecialize,
                ]
            );

            // Create class for student
            $randomClass = rand(1, ClassGroup::query()->count());
            StudentClass::firstOrCreate(
                [
                    'student_id' => $student->id,
                    'class_id' => $randomClass,
                ]
            );
        }

        // Create access token and set expiration time
        $access_token = Str::random(400);
        $expires_at = now()->addMinutes(60);
        $student->forceFill([
            'access_token' => hash('sha256', $access_token),
            'access_token_expires_at' => $expires_at,
        ])->save();

        // Create refresh token and set expiration time
        $refreshToken = Str::random(300);
        $refresh_expires_at = now()->addDays(3);
        $student->forceFill([
            'refresh_token' => hash('sha256', $refreshToken),
            'refresh_token_expires_at' => $refresh_expires_at,
        ])->save();

        return response()->json([
            'status' => 200,
            'access_token' => $access_token,
            'refreshToken' => $refreshToken,

        ], 200);
    }

    public function refreshToken(Request $request): JsonResponse
    {
        $refreshToken = hash('sha256', $request->refresh_token);

        $student = Student::where('refresh_token', $refreshToken)->first();

        if ($student) {
            // Check if the token has expired
            if (Carbon::parse($student->refresh_token_expires_at)->isPast()) {
                // Token has expired
                return response()->json([
                    'status' => 401,
                    'error' => 'Refresh token has expired'
                ], 401);
            } else {
                // Token is still valid
                $token = Str::random(400);
                $expires_at = now()->addMinutes(60);
                $student->forceFill([
                    'access_token' => hash('sha256', $token),
                    'access_token_expires_at' => $expires_at,
                ])->save();
                return response()->json([
                    'status' => 200,
                    'access_token' => $token,

                ], 200);
            }
        } else {
            // Token doesn't exist
            return response()->json(
                [
                    'status' => 401,
                    'error' => 'Refresh token doesn\'t exist',
                ],
                401
            );
        }
    }


    public function signOut(): JsonResponse
    {
        $student = auth()->user();
        $student->forceFill([
            'access_token' => null,
            'access_token_expires_at' => null,
            'refresh_token' => null,
            'refresh_token_expires_at' => null,
        ])->save();
        return response()->json([
            'status' => 200,
            'message' => 'Logged out successfully',
        ]);
    }
}
