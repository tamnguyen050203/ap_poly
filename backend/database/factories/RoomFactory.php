<?php

namespace Database\Factories;

use App\Models\Amphitheater;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Room>
 */
class RoomFactory extends Factory
{
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'amphitheater_id' => Amphitheater::query()->inRandomOrder()->first()->id,
        ];
    }
}
