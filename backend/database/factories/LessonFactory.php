<?php

namespace Database\Factories;

use App\Models\Lesson;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Lesson>
 */
class LessonFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'description' => optional($this->faker)->text,
            'code_name' => $this->faker->countryCode
        ];
    }
}
