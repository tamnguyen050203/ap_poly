<?php

namespace App\Policies;

use App\Models\RollCall;
use App\Models\Student;
use Illuminate\Auth\Access\HandlesAuthorization;

class RollCallPolicy
{
    use HandlesAuthorization;

    /**
     * Determine whether the user can view any models.
     *
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function viewAny(Student $student)
    {
        //
    }

    /**
     * Determine whether the user can view the model.
     *
     * @param  \App\Models\Student  $student
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function view(Student $student, RollCall $rollCall)
    {
        //
    }

    /**
     * Determine whether the user can create models.
     *
     * @param  \App\Models\Student  $student
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function create(Student $student)
    {
        //
    }

    /**
     * Determine whether the user can update the model.
     *
     * @param  \App\Models\Student  $student
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function update(Student $student, RollCall $rollCall)
    {
        //
    }

    /**
     * Determine whether the user can delete the model.
     *
     * @param  \App\Models\Student  $student
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function delete(Student $student, RollCall $rollCall)
    {
        //
    }

    /**
     * Determine whether the user can restore the model.
     *
     * @param  \App\Models\Student  $student
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function restore(Student $student, RollCall $rollCall)
    {
        //
    }

    /**
     * Determine whether the user can permanently delete the model.
     *
     * @param  \App\Models\Student  $student
     * @param  \App\Models\RollCall  $rollCall
     * @return \Illuminate\Auth\Access\Response|bool
     */
    public function forceDelete(Student $student, RollCall $rollCall)
    {
        //
    }
}
