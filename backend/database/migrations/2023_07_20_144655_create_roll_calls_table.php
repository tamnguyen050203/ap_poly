<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up()
    {
        Schema::create('roll_calls', function (Blueprint $table) {
            $table->id();
            $table->foreignId('schedule_id')->constrained();
            $table->foreignId('student_id')->constrained();
            $table->boolean('flag')->default(true);
        });
    }

    public function down()
    {
        Schema::dropIfExists('roll_calls');
    }
};
