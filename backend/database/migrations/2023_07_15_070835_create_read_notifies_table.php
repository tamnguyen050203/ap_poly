<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('read_notifies', function (Blueprint $table) {
            $table->id();
            $table->foreignId('notification_id')->constrained();
            $table->foreignId('student_id')->constrained();
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('read_notifies');
    }
};
