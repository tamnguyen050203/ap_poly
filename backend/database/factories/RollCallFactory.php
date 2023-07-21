<?php

namespace Database\Factories;

use App\Models\RollCall;
use App\Models\Schedule;
use App\Models\Student;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<RollCall>
 */
class RollCallFactory extends Factory
{
    public function definition()
    {
        return [
            'schedule_id' => Schedule::query()->inRandomOrder()->first()->id,
            'student_id' => Student::query()->inRandomOrder()->first()->id,
            'flag' => $this->faker->boolean(),
        ];
    }
}
