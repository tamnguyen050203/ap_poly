<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class StudentTestSchedule extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "test_schedule_id",
        "student_id",
    ];

    public function testSchedule(): HasMany
    {
        return $this->HasMany(TestSchedule::class);
    }

    public function student(): HasMany
    {
        return $this->HasMany(Student::class);
    }
}
