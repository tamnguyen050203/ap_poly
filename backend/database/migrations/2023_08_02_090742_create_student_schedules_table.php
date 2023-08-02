<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up()
    {
        Schema::create('student_schedules', function (Blueprint $table) {
            $table->id();
            $table->foreignId('student_id')->constrained('students');
            $table->foreignId('schedule_id')->constrained('schedules');
            $table->boolean('flag')->default(true);
        });
    }

    public function down()
    {
        Schema::dropIfExists('student_schedules');
    }
};
