<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class StudentSchedule extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "student_class_id",
        "schedule_id",
        "reminder_id",
        "is_alarm",
        "is_attended",
    ];

}
