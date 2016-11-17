package com.huberlulu.jassapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by huber on 14-Nov-16.
 */

public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext(){
        return mContext;
    }
}
