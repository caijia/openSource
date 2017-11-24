package com.caijia.analysisopensource.core;

import android.content.Context;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import retrofit2.Call;


/**
 * Created by cai.jia on 2017/10/25 0025.
 */

public class RepositoryInvocationHandler<T> implements InvocationHandler {

    private T apiService;
    private Object receiver;

    public RepositoryInvocationHandler(Context context, Class<T> clazz,Object receiver) {
        this.receiver = receiver;
        apiService = HttpClientManager.getInstance(context).create(clazz);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (method.isAnnotationPresent(IgnoreProxy.class)) {
                Method apiMethod = receiver.getClass().getMethod(method.getName(), method.getParameterTypes());
                return apiMethod.invoke(receiver, args);

            }else{
                Method apiMethod = apiService.getClass().getMethod(method.getName(), method.getParameterTypes());
                Call call = (Call) apiMethod.invoke(apiService, args);
                return call.execute().body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
