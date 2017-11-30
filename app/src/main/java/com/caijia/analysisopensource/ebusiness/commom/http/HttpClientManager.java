package com.caijia.analysisopensource.ebusiness.commom.http;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cai.jia on 2017/9/1 0001.
 */

public class HttpClientManager {

    private static volatile HttpClientManager instance;

    private Retrofit retrofit;
    private OkHttpClient client;

    private static final String CACHE_DIR = "onHttpCache";
    private static final String BASE_URL = "http://www.test.com";

    private HttpClientManager(Context context) {
        File cacheFile = new File(context.getCacheDir(),CACHE_DIR);
        int cacheSize = 50 * 1024 * 1024;
        Cache cache = new Cache(cacheFile,cacheSize);

        client = new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(new CacheInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static HttpClientManager getInstance(Context context) {
        if (instance == null) {
            synchronized (HttpClientManager.class) {
                if (instance == null) {
                    instance = new HttpClientManager(context);
                }
            }
        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

    public OkHttpClient getOkHttpClient() {
        return client;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}
