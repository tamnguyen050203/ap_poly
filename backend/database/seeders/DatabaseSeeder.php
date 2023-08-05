<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use App\Models\LecturerTestSchedule;
use App\Models\Schedule;
use App\Models\StudentSchedule;
use App\Models\StudentTestSchedule;
use App\Models\TestSchedule;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     */
    public function run(): void
    {
        TestSchedule::factory()->count(20)->create();
        LecturerTestSchedule::factory()->count(20)->create();
        StudentTestSchedule::factory()->count(20)->create();
        Schedule::factory()->count(60)->create();
        StudentSchedule::factory()->count(150)->create();
    }
}
