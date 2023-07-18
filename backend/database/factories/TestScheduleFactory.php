<?php

namespace Database\Factories;

use App\Models\Lesson;
use App\Models\Room;
use App\Models\Shift;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\TestSchedule>
 */
class TestScheduleFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'date' => $this->faker->dateTimeBetween('-1 week', 'now'),
            'shift_id' => Shift::query()->inRandomOrder()->first()->id,
            'lesson_id' => Lesson::query()->inRandomOrder()->first()->id,
            'room_id' => Room::query()->inRandomOrder()->first()->id,
        ];
    }
}
