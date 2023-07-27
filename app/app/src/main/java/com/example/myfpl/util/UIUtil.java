package com.example.myfpl.util;

import android.content.Context;
import android.content.res.Resources;

import androidx.core.content.res.ResourcesCompat;

public class UIUtil {
    public static int getColor(Context context, int res){
        return ResourcesCompat.getColor(context.getResources(), res, null);
    }
}
