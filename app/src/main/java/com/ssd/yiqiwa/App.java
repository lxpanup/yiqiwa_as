package com.ssd.yiqiwa;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.ssd.yiqiwa.utils.SharedPreferencesUtils;


public class App extends Application {

    private static App app;
    public static Context getAppContext() {
        return app;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
//
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);

        SharedPreferencesUtils.getInstance(this,"scdyiqiwa");
    }

}
