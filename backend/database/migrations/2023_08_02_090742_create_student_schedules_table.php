<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up()
    {
        Schema::create('student_schedules', function (Blueprint $table) {
            $table->id();
            $table->foreignId('student_class_id')->constrained('student_classes');
            $table->foreignId('schedule_id')->constrained('schedules');
            $table->string('reminder_id')->nullable();
            $table->boolean('is_alarm')->default(false);
            $table->boolean('is_attended')->default(false);
            $table->boolean('flag')->default(true);
        });
    }

    public function down()
    {
        Schema::dropIfExists('student_schedules');
    }
};
