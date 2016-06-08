package com.shuh.ui.utils;

import android.util.Log;

/**
 * Created by Administrator on 2016/4/29 0029.
 */
public class PP {
    private static final boolean isDebug = true;
    public static void d(String msg){
        if(isDebug)
            Log.d("Debug", msg);
    }

    public static void e(String msg, Throwable tr){
        if(isDebug)
            Log.d("Debug", msg, tr);
    }
}
