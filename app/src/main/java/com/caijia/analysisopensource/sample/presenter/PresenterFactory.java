package com.caijia.analysisopensource.sample.presenter;

import android.content.Context;

import com.caijia.analysisopensource.core.ProxyUtil;

/**
 * Created by cai.jia on 2017/10/26 0026.
 */

public class PresenterFactory {

    private static volatile PresenterFactory instance;

    private ApiPresenter presenter;

    private PresenterFactory(Context context) {
        ApiPresenter presenterImpl = new ApiPresenterImpl(context);
        presenter = ProxyUtil.getPresenterProxy(presenterImpl);
    }

    public static PresenterFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (PresenterFactory.class) {
                if (instance == null) {
                    instance = new PresenterFactory(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public ApiPresenter getPresenter() {
        return presenter;
    }
}
