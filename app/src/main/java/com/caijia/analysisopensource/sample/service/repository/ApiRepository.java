package com.caijia.analysisopensource.sample.service.repository;

import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/21.
 */

public interface ApiRepository {

    Response<List<TestResult>> isShowData(String cacheControl) throws Exception;
}
