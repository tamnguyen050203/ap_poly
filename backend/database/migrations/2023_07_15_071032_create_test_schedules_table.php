<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('test_schedules', function (Blueprint $table) {
            $table->id();
            $table->date('date')->between('2021-08-01', '2023-09-31');
            $table->foreignId('shift_id')->constrained();
            $table->foreignId('lesson_id')->constrained();
            $table->foreignId('room_id')->constrained();
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('test_schedules');
    }
};
