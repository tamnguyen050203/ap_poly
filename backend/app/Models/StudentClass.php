<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class StudentClass extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "student_id",
        "class_group_id",
    ];

    public function student(): hasMany
    {
        return $this->hasMany(Student::class);
    }

    public function classGroup(): hasMany
    {
        return $this->hasMany(ClassGroup::class);
    }
}
