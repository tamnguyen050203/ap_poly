<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class StudentClass extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "student_id",
        "class_group_id",
    ];

    public function student(): belongsTo
    {
        return $this->belongsTo(Student::class);
    }

    public function class_group(): belongsTo
    {
        return $this->belongsTo(ClassGroup::class);
    }
}
