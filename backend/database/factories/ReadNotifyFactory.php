<?php

namespace Database\Factories;

use App\Models\Notification;
use App\Models\Student;
use Illuminate\Database\Eloquent\Factories\Factory;

class ReadNotifyFactory extends Factory
{
    public function definition(): array
    {
        return [
            'notification_id' => Notification::query()->inRandomOrder()->first()->id,
            'student_id' => Student::query()->inRandomOrder()->first()->id,
        ];
    }
}
