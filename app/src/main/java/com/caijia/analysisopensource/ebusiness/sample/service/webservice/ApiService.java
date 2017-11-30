package com.caijia.analysisopensource.ebusiness.sample.service.webservice;

import com.caijia.analysisopensource.ebusiness.commom.model.BaseResponse;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult;
import com.caijia.analysisopensource.ebusiness.sample.service.model.SampleResult1;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by cai.jia on 2017/11/21.
 */

public interface ApiService {

    @GET()
    Call<BaseResponse<List<SampleResult>>> isShowData(
            @Url String url,
            @Header("Cache-Control") String cacheControl);

    @POST()
    Call<SampleResult1> getData(@Url String url,
                                @Query("tag") String tag,
                                @Body Map<String, Object> body);

    @Multipart
    @POST()
    Call<Object> uploadImage(@Url String url, @PartMap Map<String, RequestBody> map,
                             @Part MultipartBody.Part part);

}
