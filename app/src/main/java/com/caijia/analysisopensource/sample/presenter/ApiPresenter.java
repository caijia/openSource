package com.caijia.analysisopensource.sample.presenter;

import com.caijia.analysisopensource.core.ComponentLifecycle;
import com.caijia.analysisopensource.sample.callback.ApiView;
import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/21.
 */

public interface ApiPresenter {

    Response<List<TestResult>> isShowData(ComponentLifecycle lifecycle, String cacheControl, ApiView view);
}
