<?php

namespace Database\Factories;

use App\Models\Lecturer;
use App\Models\TestSchedule;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\LecturerTestSchedule>
 */
class LecturerTestScheduleFactory extends Factory
{
    public function definition(): array
    {
        return [
            'lecturer_id' => Lecturer::query()->inRandomOrder()->first()->id,
             'test_schedule_id' => TestSchedule::query()->inRandomOrder()->first()->id,
        ];
    }
}
