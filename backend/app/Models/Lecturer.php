<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Lecturer extends Model
{
    use HasFactory;

    public $timestamps = false;

    protected $fillable = [
        "name",
        "email",
        "specialize_id",
    ];

    public function specialize(): BelongsTo
    {
        return $this->belongsTo(Specialize::class);
    }

    public function getNickName()
    {
        $email = $this->email;
        $nickName = substr($email, 0, strpos($email, '@'));
        return $nickName;
    }
}
