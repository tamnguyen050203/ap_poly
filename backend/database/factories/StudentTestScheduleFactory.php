<?php

namespace Database\Factories;

use App\Models\Student;
use App\Models\TestSchedule;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\StudentTestSchedule>
 */
class StudentTestScheduleFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'student_id' => Student::query()->inRandomOrder()->first()->id,
            'test_schedule_id' => TestSchedule::query()->inRandomOrder()->first()->id,
        ];
    }
}
