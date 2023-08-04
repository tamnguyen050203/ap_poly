<?php

namespace App\Models;

use App\Enums\NotifyType;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Notification extends Model
{
    use HasFactory;

    protected $fillable = [
        "name",
        "content",
        "author",
        "type",
        "created_date",
        "updated_date",
    ];

    public function getTypeNameAttribute(): string
    {
        return NotifyType::getArrayView()[$this->type];
    }

}
