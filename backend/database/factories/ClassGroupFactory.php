<?php

namespace Database\Factories;

use App\Models\Lecturer;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\ClassGroup>
 */
class ClassGroupFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'link' => optional($this->faker)->url,
            'lecturer_id' => Lecturer::query()->inRandomOrder()->first()->id,
        ];
    }
}
