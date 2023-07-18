<?php

namespace App\Enums;

use Illuminate\Validation\Rules\Enum;

class NotifyType extends Enum
{
    public const ACTIVE = 1;
    public const WORK = 2;
    public const STUDY = 3;
    public const FEE = 4;

    public static function getArrayView(): array
    {
        return [
            self::ACTIVE => 'Hoạt động',
            self::WORK => 'Lảm việc',
            self::STUDY => 'Học tập',
            self::FEE => 'Phí',
        ];
    }

    public static function getKeyByValue($value): string
    {
        return array_search($value, self::getArrayView(), true);
    }
}
