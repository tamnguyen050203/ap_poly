<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Notification;
use App\Models\ReadNotify;
use Illuminate\Http\Request;


class NotificationController extends Controller
{
    public function __construct()
    {
        $this->model = (new Notification())->query();
    }

    function getNotify(Request $request)
    {
        $type = $request->type;
        $amount = $request->amount;
        $notify = $this->model
            ->when($type != 0, function ($query) use ($type) {
                return $query->where('type', $type);
            })
            ->select('id', 'title', 'content', 'author', 'type', 'created_at', 'updated_at')
            ->latest('updated_at')
            ->paginate($amount ?? 15);

        foreach ($notify as $notification) {
            $notification->type = $notification->getTypeNameAttribute();
            // Use ReadNotify model to set isRead for notify
            $readNotify = ReadNotify::where([
                ['student_id', auth()->user()->id],
                ['notification_id', $notification->id],
            ])->first();
            $notification->isRead = (bool) $readNotify;
        }

        return response()->json([
            'status' => 200,
            'notify' => $notify,
        ]);
    }

    function readNotify(Request $request)
    {
        $notifyId = $request->notifyId;
        $studentId = auth()->user()->id;

        $readNotify = ReadNotify::where([
            ['student_id', $studentId],
            ['notification_id', $notifyId],
        ])->first();

        if ($readNotify) {
            return response()->json([
                'status' => 200,
                'message' => 'Đã đọc thông báo này',
            ]);
        }

        ReadNotify::create([
            'student_id' => $studentId,
            'notification_id' => $notifyId,
        ]);

        return response()->json([
            'status' => 200,
            'message' => 'Đã đọc thông báo này',
        ]);
    }

    function readAllNotify()
    {
        $studentId = auth()->user()->id;

        // Get all id of notification
        $notifyIds = $this->model->select('id')->get()->pluck('id');

        // Get all id of read notify
        $readNotifyIds = ReadNotify::where('student_id', $studentId)->get()->pluck('notification_id');

        // Get all id of unread notify
        $unreadNotifyIds = $notifyIds->diff($readNotifyIds);

        // Create read notify for all unread notify
        foreach ($unreadNotifyIds as $notifyId) {
            ReadNotify::create([
                'student_id' => $studentId,
                'notification_id' => $notifyId,
            ]);
        }

        return response()->json([
            'status' => 200,
            'message' => 'Đã đọc tất cả thông báo',
        ]);
    }
}
