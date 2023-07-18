<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Notification extends Model
{
    use HasFactory;

    public $timestamps = true;

    protected $fillable = [
        "name",
        "content",
        "author",
        "type",
        "created_date",
        "updated_date",
    ];

    public function getDateFormat(): string
    {
        return 'Y-m-d H:i';
    }
}
