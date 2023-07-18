<?php

namespace Database\Factories;

use App\Models\Specialize;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Student>
 */
class StudentFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'email' => $this->faker->unique()->safeEmail,
            'phone' => $this->faker->phoneNumber,
            'dob' => $this->faker->date(),
            'avatar' => $this->faker->imageUrl(),
            'specialize_id' => Specialize::query()->inRandomOrder()->first()->id,
        ];
    }
}
