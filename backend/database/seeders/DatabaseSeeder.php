<?php

namespace Database\Seeders;

// use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use App\Models\Amphitheater;
use App\Models\ClassGroup;
use App\Models\Lecturer;
use App\Models\LecturerTestSchedule;
use App\Models\Lesson;
use App\Models\Notification;
use App\Models\ReadNotify;
use App\Models\Room;
use App\Models\Schedule;
use App\Models\Shift;
use App\Models\Specialize;
use App\Models\Student;
use App\Models\StudentClass;
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
        Amphitheater::factory()->count(2)->create();
        Shift::factory()->count(6)->create();
        Room::factory()->count(40)->create();
        Specialize::factory()->count(10)->create();
        Lesson::factory()->count(10)->create();
        Notification::factory()->count(50)->create();
        Student::factory()->count(30)->create();
        ReadNotify::factory()->count(20)->create();
        Lecturer::factory()->count(10)->create();
        ClassGroup::factory()->count(2)->create();
        Schedule::factory()->count(50)->create();
        TestSchedule::factory()->count(20)->create();
        LecturerTestSchedule::factory()->count(20)->create();
        StudentTestSchedule::factory()->count(20)->create();
        StudentClass::factory()->count(30)->create();
        StudentSchedule::factory()->count(100)->create();
    }
}
