<?php

namespace App\Policies;

use App\Models\Lesson;
use App\Models\Student;
use Illuminate\Auth\Access\Response;

class LessonPolicy
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
    public function view(Student $student, Lesson $lesson): bool
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
    public function update(Student $student, Lesson $lesson): bool
    {
        //
    }

    /**
     * Determine whether the user can delete the model.
     */
    public function delete(Student $student, Lesson $lesson): bool
    {
        //
    }

    /**
     * Determine whether the user can restore the model.
     */
    public function restore(Student $student, Lesson $lesson): bool
    {
        //
    }

    /**
     * Determine whether the user can permanently delete the model.
     */
    public function forceDelete(Student $student, Lesson $lesson): bool
    {
        //
    }
}
