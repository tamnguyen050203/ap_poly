<?php

namespace App\Helpers;

class RandomInfo
{
    public static function randomDateOfBirth($start, $end)
    {
        $timestamp = mt_rand(strtotime($start), strtotime($end));
        return date('Y-m-d', $timestamp);
    }

    public static function randomPhone()
    {
        $phone = '0';
        for ($i = 0; $i < 9; $i++) {
            $phone .= rand(0, 9);
        }
        return $phone;
    }
}
