package com.caijia.analysisopensource.sample.callback;

import android.support.annotation.Nullable;

import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/23.
 */

public interface ApiView {

    void onShowData(@Nullable Response<List<TestResult>> result);
}
