<?php

namespace App\Policies;

use App\Models\LecturerTestSchedule;
use App\Models\Student;
use Illuminate\Auth\Access\Response;

class LecturerTestSchedulePolicy
{
    /**
     * Determine whether the user can view any models.
     */
    public function viewAny(Student $student): bool
    {
        //
    }

    /**
     * Determine whether the user can view the model.
     */
    public function view(Student $student, LecturerTestSchedule $lecturerTestSchedule): bool
    {
        //
    }

    /**
     * Determine whether the user can create models.
     */
    public function create(Student $student): bool
    {
        //
    }

    /**
     * Determine whether the user can update the model.
     */
    public function update(Student $student, LecturerTestSchedule $lecturerTestSchedule): bool
    {
        //
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(Student $student, LecturerTestSchedule $lecturerTestSchedule): bool
    {
        //
    }

    /**
     * Determine whether the user can restore the model.
     */
    public function restore(Student $student, LecturerTestSchedule $lecturerTestSchedule): bool
    {
        //
    }

    /**
     * Determine whether the user can permanently delete the model.
     */
    public function forceDelete(Student $student, LecturerTestSchedule $lecturerTestSchedule): bool
    {
        //
    }
}
