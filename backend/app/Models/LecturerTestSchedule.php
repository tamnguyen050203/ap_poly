<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;
use Illuminate\Database\Eloquent\Relations\HasMany;

class LecturerTestSchedule extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "lecturer_id",
        "test_schedule_id"
    ];

    public function lecturer(): HasMany
    {
        return $this->hasMany(Lecturer::class);
    }

    public function testSchedule(): BelongsTo
    {
        return $this->belongsTo(TestSchedule::class);
    }
}
