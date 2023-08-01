<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class LecturerTestSchedule extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "lecturer_id",
        "test_schedule_id"
    ];

    public function lecturer(): BelongsTo
    {
        return $this->BelongsTo(Lecturer::class);
    }

    public function testSchedule(): BelongsTo
    {
        return $this->belongsTo(TestSchedule::class);
    }
}
