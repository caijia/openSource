package com.caijia.analysisopensource.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * 当异步任务回调时,必须确保View是否可用才回调
 * Created by cai.jia on 2017/9/13 0013.
 */

public class ComponentLifecycle {

    private Activity activity;
    private Fragment fragment;
    private Context context;

    public static ComponentLifecycle newInstance(Activity activity) {
        return new ComponentLifecycle(activity);
    }

    public static ComponentLifecycle newInstance(Fragment fragment) {
        return new ComponentLifecycle(fragment);
    }

    public static ComponentLifecycle newInstance(Context context) {
        return new ComponentLifecycle(context);
    }

    private ComponentLifecycle(Activity activity) {
        this.activity = activity;
    }

    private ComponentLifecycle(Fragment fragment) {
        this.fragment = fragment;
    }

    private ComponentLifecycle(Context context) {
        this.context = context;
    }

    public boolean canUse(){
        return (activity != null && !activity.isFinishing())
                || (fragment != null && fragment.getContext() != null && fragment.getView() != null)
                || context != null;
    }
}
