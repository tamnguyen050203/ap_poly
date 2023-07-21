<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('students', function (Blueprint $table) {
            $table->id();
            $table->string('name')->nullable(false);
            $table->string('email')->nullable(false)->unique();
            $table->string('phone')->nullable(false)->unique()->nullable();
            $table->date('dob')->nullable(false)->nullable();
            $table->text('avatar')->nullable();
            $table->foreignId('specialize_id')->nullable()->constrained();
            $table->string('provider_id')->nullable();
            $table->text('access_token')->nullable();
            $table->text('refresh_token')->nullable();
            $table->dateTime('access_token_expires_at')->nullable();
            $table->dateTime('refresh_token_expires_at')->nullable();
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('students');
    }
};
