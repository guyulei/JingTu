package com.guyulei.jingtu.ui.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by A on 2017/8/21.
 */

public class JingTuApp extends Application {


    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
