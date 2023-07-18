<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration {
    public function up(): void
    {
        Schema::create('amphitheaters', function (Blueprint $table) {
            $table->id();
            $table->string('name');
            $table->boolean('flag')->default(true);
        });
    }

    public function down(): void
    {
        Schema::dropIfExists('amphitheaters');
    }
};
