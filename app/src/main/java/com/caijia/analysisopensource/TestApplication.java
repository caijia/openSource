package com.caijia.analysisopensource;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by cai.jia on 2017/11/21.
 */

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
