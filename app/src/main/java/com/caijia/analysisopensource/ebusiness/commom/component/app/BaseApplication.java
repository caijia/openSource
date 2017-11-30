package com.caijia.analysisopensource.ebusiness.commom.component.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;


/**
 * Created by cai.jia on 2017/8/29 0029.
 */

public abstract class BaseApplication extends Application {

    private AppDelegateManager delegateManager;

    @Override
    public final void onCreate() {
        super.onCreate();
        onCreated();
        delegateManager.onCreate(this);
    }

    protected abstract void onCreated();

    @Override
    public void onTerminate() {
        super.onTerminate();
        delegateManager.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        delegateManager.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        delegateManager.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        delegateManager.onTrimMemory(level);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        delegateManager = new AppDelegateManager();
        delegateManager.attachBaseContext(base);
    }

    public void addAppDelegate(ApplicationDelegate delegate) {
        delegateManager.addAppDelegate(delegate);
    }
}
