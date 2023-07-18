<?php

namespace Database\Factories;

use App\Models\Specialize;
use Illuminate\Database\Eloquent\Factories\Factory;

class LecturerFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'email' => $this->faker->unique()->safeEmail,
            'specialize_id' => Specialize::query()->inRandomOrder()->first()->id,
        ];
    }
}
