<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Room extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "name",
        "amphitheater_id",
    ];

    public function amphitheater()
    {
        return $this->belongsTo(Amphitheater::class);
    }
}
