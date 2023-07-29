<?php

namespace App\Enums;

use Illuminate\Validation\Rules\Enum;

class NotifyType extends Enum
{
    public const ALL = 0;
    public const ACTIVITY = 1;
    public const JOB = 2;
    public const STUDY = 3;
    public const FEE = 4;

    public static function getKeyByValue($value): string
    {
        return array_search($value, self::getArrayView(), true);
    }

    public static function getArrayView(): array
    {
        return [
            self::ALL => 'Tất Cả',
            self::ACTIVITY => 'Hoạt Động',
            self::JOB => 'Việc Làm',
            self::STUDY => 'Học Tập',
            self::FEE => 'Học  Phí',
        ];
    }
}
