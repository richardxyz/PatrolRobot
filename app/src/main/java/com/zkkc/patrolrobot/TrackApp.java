package com.zkkc.patrolrobot;

import android.app.Application;
import android.content.Context;



/**
 * Created by ShiJunRan on 2019/2/14
 * Application类
 */
public class TrackApp extends Application {
    private static Context mContext;//全局上下文对象

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }
    public static Context getContext() {
        return mContext;
    }
}
