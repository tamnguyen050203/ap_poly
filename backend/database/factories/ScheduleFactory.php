<?php

namespace Database\Factories;

use App\Models\ClassGroup;
use App\Models\Lesson;
use App\Models\Room;
use App\Models\Shift;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Schedule>
 */
class ScheduleFactory extends Factory
{
    public function definition(): array
    {
        return [
            'date' => $this->faker->date(),
            'detail' => $this->faker->text(),
            'shift_id' => Shift::query()->inRandomOrder()->first()->id,
            'room_id' => Room::query()->inRandomOrder()->first()->id,
            'lesson_id' => Lesson::query()->inRandomOrder()->first()->id,
            'class_group_id' => ClassGroup::query()->inRandomOrder()->first()->id,
        ];
    }
}
