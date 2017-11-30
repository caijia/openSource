package com.caijia.analysisopensource.ebusiness.commom.component.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by cai.jia on 2017/8/29 0029.
 */

public interface ApplicationDelegate {

     void onCreate(Application application);

     void onTerminate();

     void onConfigurationChanged(Configuration newConfig);

     void onLowMemory();

     void onTrimMemory(int level);

     void attachBaseContext(Context base);
}
