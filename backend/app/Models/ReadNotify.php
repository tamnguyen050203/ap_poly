<?php

namespace App\Models;

use Couchbase\User;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;

class ReadNotify extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "student_id",
        "notification_id",
    ];

    public function student(): HasMany
    {
        return $this->HasMany(Student::class);
    }

    public function notification(): HasMany
    {
        return $this->HasMany(Notification::class);
    }
}
