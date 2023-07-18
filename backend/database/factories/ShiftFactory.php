<?php

namespace Database\Factories;

use DateInterval;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Shift>
 */
class ShiftFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $start_time = $this->faker->dateTimeBetween('-1 week', 'now');
        $end_time = clone $start_time;
        $end_time->add(new DateInterval('PT2H'));

        return [
            'name' => $this->faker->name,
            'start_time' => $start_time,
            'end_time' => $end_time,
        ];
    }
}
