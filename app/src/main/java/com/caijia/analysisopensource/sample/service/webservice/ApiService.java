package com.caijia.analysisopensource.sample.service.webservice;

import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by cai.jia on 2017/11/21.
 */

public interface ApiService {

    @GET()
    Call<Response<List<TestResult>>> isShowData(
            @Url String url,
            @Header("Cache-Control") String cacheControl);
}
