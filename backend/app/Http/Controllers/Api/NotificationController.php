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
        $notify = $this->model
            ->when($type != 0, function ($query) use ($type) {
                return $query->where('type', $type);
            })
            ->select('id', 'title', 'content', 'author', 'type', 'created_at', 'updated_at')
            ->latest('updated_at')
            ->paginate(5);

        
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
}
