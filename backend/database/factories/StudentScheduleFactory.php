<?php

namespace Database\Factories;

use App\Models\ClassGroup;
use App\Models\StudentClass;
use App\Models\StudentSchedule;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends Factory<StudentSchedule>
 */
class StudentScheduleFactory extends Factory
{
    public function definition()
    {
        $student_class_id = StudentClass::query()->inRandomOrder()->first()->id;
        $class_group_id = StudentClass::query()
            ->where('id', '=', $student_class_id)->first()->class_group_id;
        $schedule_id = ClassGroup::query()->join(
            'schedules',
            'class_groups.id',
            '=',
            'schedules.class_group_id')
            ->where('class_groups.id', $class_group_id)
            ->inRandomOrder()->first()->id;
        return [
            'student_class_id' => $student_class_id,
            'schedule_id' => $schedule_id,
        ];
    }
}
