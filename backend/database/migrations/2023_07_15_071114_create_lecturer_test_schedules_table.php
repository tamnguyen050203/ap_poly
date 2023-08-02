<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('lecturer_test_schedules', function (Blueprint $table) {
            $table->id();
            $table->foreignId('lecturer_id')->constrained();
            $table->foreignId('test_schedule_id')->constrained();
            $table->boolean('is_attended')->default(false);
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('lecturer_test_schedules');
    }
};
