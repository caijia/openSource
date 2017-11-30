package com.caijia.analysisopensource.ebusiness.sample.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.caijia.analysisopensource.ebusiness.commom.http.CacheControl;
import com.caijia.analysisopensource.ebusiness.commom.model.BaseResponse;
import com.caijia.analysisopensource.ebusiness.commom.viewmodel.BaseViewModel;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult;
import com.caijia.analysisopensource.ebusiness.sample.service.repository.ApiRepository;
import com.caijia.analysisopensource.ebusiness.sample.service.repository.RepositoryFactory;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * Created by cai.jia on 2017/11/24.
 */

public class SampleViewModel extends BaseViewModel<BaseResponse<List<SampleResult>>> {

    public SampleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<BaseResponse<List<SampleResult>>> getData() {
        ApiRepository apiRepository = RepositoryFactory.getInstance(getApplication())
                .getApiRepository();
        Callable<BaseResponse<List<SampleResult>>> callable = () -> apiRepository
                .isShowData(CacheControl.network());
        loadData(callable);
        return this.data;
    }
}
