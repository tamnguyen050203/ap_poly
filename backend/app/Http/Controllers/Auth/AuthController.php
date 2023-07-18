<?php

namespace App\Http\Controllers\Auth;

use App\Models\Student;
use Illuminate\Http\JsonResponse;
use Illuminate\Routing\Controller;
use Illuminate\Http\Request;
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
            $student = Student::firstOrCreate(
                [
                    'email' => $request->email,
                    'name' => $request->name,
                    'avatar' => $request->avatar,
                    'provider_id' => $request->provider_id,
                    'specialize_id' => $request->specialize_id,
                ]
            );
        }

        // Create access token and set expiration time
        $access_token = Str::random(500);
        $expires_at = now()->addMinutes(60);
        $student->forceFill([
            'access_token' => hash('sha256', $access_token),
            'access_token_expires_at' => $expires_at,
        ])->save();

        // Create refresh token and set expiration time
        $refreshToken = Str::random(350);
        $refresh_expires_at = now()->addDays(350);
        $student->forceFill([
            'refresh_token' => hash('sha256', $refreshToken),
            'refresh_token_expires_at' => $refresh_expires_at,
        ])->save();

        return response()->json([
            'access_token' => $access_token,
            'refreshToken' => $refreshToken,
            'user' => $student,
        ]);
    }

    public function refreshToken(Request $request): JsonResponse
    {
        $refreshToken = $request->refreshToken;

        $student = Student::where('refresh_token', hash('sha256', $refreshToken))->first();

        if ($student) {
            // Check if the token has expired
            if (Carbon::parse($student->refresh_token_expires_at)->isPast()) {
                // Token has expired
                return response()->json(['error' => 'Refresh token has expired'], 401);
            } else {
                // Token is still valid
                $token = Str::random(500);
                $student->forceFill([
                    'api_token' => hash('sha256', $token),
                ])->save();
                return response()->json([
                    'access_token' => $token,
                ]);
            }
        } else {
            // Token doesn't exist
            return response()->json(['error' => 'Refresh token doesn\'t exist'], 401);
        }
    }


    public function signOut(): JsonResponse
    {
        dd(auth()->user());
        return response()->json([
            'message' => 'Logged out successfully'
        ]);
    }

}
