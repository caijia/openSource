package com.caijia.analysisopensource.sample.service.repository;

import android.content.Context;

import com.caijia.analysisopensource.core.HttpClientManager;
import com.caijia.analysisopensource.sample.service.model.Response;
import com.caijia.analysisopensource.sample.service.model.TestResult;
import com.caijia.analysisopensource.sample.service.webservice.ApiService;

import java.util.List;

/**
 * 不能用Gson自动处理结果的请求，需要手动解析
 * Created by cai.jia on 2017/11/21.
 */
public class ApiRepositoryImpl implements ApiRepository{

    private ApiService apiService;
    private static final String TEST_URL = "https://api.6383.com/LiveChatRoom/GetChatRoomList";

    public ApiRepositoryImpl(Context context) {
        apiService = HttpClientManager.getInstance(context).create(ApiService.class);
    }

    @Override
    public Response<List<TestResult>> isShowData(String cacheControl) throws Exception{
        return apiService.isShowData(TEST_URL,cacheControl).execute().body();
    }

}
