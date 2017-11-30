package com.caijia.analysisopensource.ebusiness.commom.component.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cai.jia on 2017/10/23 0023.
 */

public class AppDelegateManager {

    private List<ApplicationDelegate> applicationDelegates;

    public AppDelegateManager() {
        applicationDelegates = new ArrayList<>();
    }

    public void onCreate(Application application) {
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.onCreate(application);
            }
        }
    }

    public void onTerminate() {
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.onTerminate();
            }
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.onConfigurationChanged(newConfig);
            }
        }
    }

    public void onLowMemory() {
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.onLowMemory();
            }
        }
    }

    public void onTrimMemory(int level) {
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.onTrimMemory(level);
            }
        }
    }

    public void attachBaseContext(Context base) {
        if (applicationDelegates == null) {
            applicationDelegates = new ArrayList<>();
        }
        if (!delegateIsEmpty()) {
            for (ApplicationDelegate applicationDelegate : applicationDelegates) {
                applicationDelegate.attachBaseContext(base);
            }
        }
    }

    public void addAppDelegate(ApplicationDelegate delegate) {
        if (delegate != null && !applicationDelegates.contains(delegate)) {
            applicationDelegates.add(delegate);
        }
    }

    private boolean delegateIsEmpty() {
        return applicationDelegates == null || applicationDelegates.isEmpty();
    }
}
