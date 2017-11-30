package com.caijia.analysisopensource.ebusiness.sample.service.repository;

import com.caijia.analysisopensource.ebusiness.commom.model.BaseResponse;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult1;

import java.util.List;

/**
 * Created by cai.jia on 2017/11/21.
 */

public interface ApiRepository {

    BaseResponse<List<SampleResult>> isShowData(String cacheControl) throws Exception;

    SampleResult1 getData() throws Exception;

    Object uploadImage(String filePath) throws Exception;
}
