<?php

use App\Http\Controllers\Api\NotificationController;
use App\Http\Controllers\Api\ScheduleController;
use App\Http\Controllers\Api\StudentController;
use App\Http\Controllers\Api\TestScheduleController;
use App\Http\Controllers\Auth\AuthController;
use Illuminate\Support\Facades\Route;


route::group(['prefix' => 'auth'], function () {
    route::post('login', [AuthController::class, 'login']);
    route::post('refresh-token', [AuthController::class, 'refreshToken']);
    route::group(['middleware' => 'tokenIsValid'], function () {
        route::post('logout', [AuthController::class, 'signOut']);
    });
});

route::group(['prefix' => 'student', 'middleware' => 'tokenIsValid'], function () {
    route::get('info', [StudentController::class, 'getInfo']);
    route::get('notifies/{type}', [NotificationController::class, 'getNotify']);
    route::get('schedules', [ScheduleController::class, 'getSchedules']);
    route::get('testSchedules', [TestScheduleController::class, 'getTestSchedules']);
    route::post('readNotify/{notifyId}', [NotificationController::class, 'readNotify']);
    route::post('readAllNotify', [NotificationController::class, 'readAllNotify']);
    route::post('setAlarmSchedule', [ScheduleController::class, 'setAlarmSchedule']);
});
