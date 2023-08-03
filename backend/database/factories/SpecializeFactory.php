<?php

namespace Database\Factories;

use App\Models\Specialize;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<Specialize>
 */
class SpecializeFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'description' => $this->faker->text,
        ];
    }
}
