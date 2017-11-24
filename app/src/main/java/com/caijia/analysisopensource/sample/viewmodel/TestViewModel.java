package com.caijia.analysisopensource.sample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.caijia.analysisopensource.core.CacheControl;
import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;
import com.caijia.analysisopensource.sample.service.repository.ApiRepository;
import com.caijia.analysisopensource.sample.service.repository.RepositoryFactory;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by cai.jia on 2017/11/24.
 */

public class TestViewModel extends AbsViewModel<Response<List<TestResult>>> {

    public TestViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Response<List<TestResult>>> getData() {
        ApiRepository apiRepository = RepositoryFactory.getInstance(getApplication()).getApiRepository();
        Callable<Response<List<TestResult>>> callable = () -> apiRepository
                .isShowData(CacheControl.network());
        loadData(callable);
        return data;
    }
}
