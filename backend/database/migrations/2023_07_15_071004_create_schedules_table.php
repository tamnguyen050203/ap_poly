<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    public function up(): void
    {
        Schema::create('schedules', function (Blueprint $table) {
            $table->id();
            $table->date('date')->default(now());
            $table->string('detail');
            $table->foreignId('shift_id')->constrained();
            $table->foreignId('room_id')->constrained();
            $table->foreignId('lesson_id')->constrained();
            $table->foreignId('class_group_id')->constrained();
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('schedules');
    }
};
