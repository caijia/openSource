package com.caijia.analysisopensource.ebusiness.sample.service.repository;

import android.content.Context;
import android.support.annotation.WorkerThread;

import com.caijia.analysisopensource.ebusiness.commom.http.HttpClientManager;
import com.caijia.analysisopensource.ebusiness.commom.model.BaseResponse;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult1;
import com.caijia.analysisopensource.ebusiness.sample.service.webservice.ApiService;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 不能用Gson自动处理结果的请求，需要手动解析
 * Created by cai.jia on 2017/11/21.
 */
public class ApiRepositoryImpl implements ApiRepository{

    private ApiService apiService;
    private static final String TEST_URL = "https://api.6383.com/LiveChatRoom/GetChatRoomList";
    private static final String TEST_URL1 = "http://gou.izhongpei.com/ytsfapp/mobilese/home/main";
    private static final String TEST_URL2 = "http://admintest.implus100.com/agent/interface/marke_Interface.jsp";

    public ApiRepositoryImpl(Context context) {
        apiService = HttpClientManager.getInstance(context).create(ApiService.class);
    }

    @WorkerThread
    @Override
    public BaseResponse<List<SampleResult>> isShowData(String cacheControl) throws Exception{
        return apiService.isShowData(TEST_URL,cacheControl).execute().body();
    }

    @WorkerThread
    @Override
    public SampleResult1 getData() throws Exception {
        Map<String,Object> jo = new HashMap<>();
        jo.put("device", "android");
        jo.put("model", "ZTE B2015");
        jo.put("version", "1.2.2");
        return apiService.getData(TEST_URL1,"1511754876720",jo).execute().body();
    }

    @Override
    public Object uploadImage(String filePath) throws Exception {
        Map<String, RequestBody> map = new HashMap<>();
        map.put("method", RequestBody.create(null,"uploadHeadImg"));
        map.put("random", RequestBody.create(null, "1639084458"));
        map.put("signature", RequestBody.create(null, "A8B967C75C5C853C35D140A186F96EAA"));
        map.put("userId", RequestBody.create(null, "297ebe0e55dd69430155de1e99db006e"));

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/octet-stream"),
                new File(filePath));
        MultipartBody.Part part = MultipartBody.Part.createFormData("uploadHeadImg", "filename.jpg", requestBody);
        return apiService.uploadImage(TEST_URL2,map,part).execute().body();
    }
}
