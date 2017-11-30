package com.caijia.analysisopensource.ebusiness.sample.service.repository;

import android.content.Context;

/**
 * Created by cai.jia on 2017/10/26 0026.
 */

public class RepositoryFactory {

    private static volatile RepositoryFactory instance;

    private ApiRepository repository;

    private RepositoryFactory(Context context) {
        repository = new ApiRepositoryImpl(context);
    }

    public static RepositoryFactory getInstance(Context context) {
        if (instance == null) {
            synchronized (RepositoryFactory.class) {
                if (instance == null) {
                    instance = new RepositoryFactory(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    public ApiRepository getApiRepository() {
        return repository;
    }
}
