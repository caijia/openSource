package com.caijia.analysisopensource.sample.presenter;

import android.content.Context;

import com.caijia.analysisopensource.core.ComponentLifecycle;
import com.caijia.analysisopensource.sample.callback.ApiView;
import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;
import com.caijia.analysisopensource.sample.service.repository.ApiRepository;
import com.caijia.analysisopensource.sample.service.repository.RepositoryFactory;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/23.
 */

public class ApiPresenterImpl implements ApiPresenter {

    private ApiRepository apiRepository;

    public ApiPresenterImpl(Context context){
        apiRepository = RepositoryFactory.getInstance(context).getApiRepository();
    }

    @Override
    public Response<List<TestResult>> isShowData(ComponentLifecycle lifecycle, String cacheControl,
                                                 ApiView view) {
        try {
            return apiRepository.isShowData(cacheControl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
