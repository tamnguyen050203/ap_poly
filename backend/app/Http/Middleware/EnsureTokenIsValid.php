<?php

namespace App\Http\Middleware;

use App\Models\Student;
use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Carbon;

class EnsureTokenIsValid
{
    public function handle(Request $request, Closure $next)
    {
        // Get the token from the database
        $token = Student::where('access_token', hash('sha256', $request->bearerToken()))->first();

        if ($token) {
            // Check if the token has expired
            if (Carbon::parse($token->access_token_expires_at)->isPast()) {
                // Token has expired
                return response()->json(['error' => 'Token has expired'], 401);
            } else {
                $student = Student::find($token->id);
                auth()->login($student);
                // Token is still valid
                return $next($request);
            }
        } else {
            // Token doesn't exist
            return response()->json(['error' => 'Token doesn\'t exist'], 401);
        }
    }
}
