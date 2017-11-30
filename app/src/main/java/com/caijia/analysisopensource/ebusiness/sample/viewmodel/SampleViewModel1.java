package com.caijia.analysisopensource.ebusiness.sample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.caijia.analysisopensource.ebusiness.commom.viewmodel.BaseViewModel;
import com.caijia.analysisopensource.ebusiness.sample.service.repository.ApiRepository;
import com.caijia.analysisopensource.ebusiness.sample.service.repository.RepositoryFactory;

import java.util.concurrent.Callable;

/**
 * Created by cai.jia on 2017/11/24.
 */

public class SampleViewModel1 extends BaseViewModel<Object> {

    public SampleViewModel1(@NonNull Application application) {
        super(application);
    }

    public LiveData<Object> getData(String path) {
        ApiRepository apiRepository = RepositoryFactory.getInstance(getApplication())
                .getApiRepository();
        Callable<Object> callable = () -> apiRepository.uploadImage(path);
        loadData(callable);
        return data;
    }
}
