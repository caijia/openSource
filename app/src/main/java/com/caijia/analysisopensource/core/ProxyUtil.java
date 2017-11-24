package com.caijia.analysisopensource.core;

import android.content.Context;

import java.lang.reflect.Proxy;

/**
 * Created by cai.jia on 2017/10/26 0026.
 */

public class ProxyUtil {

    /**
     * 获取仓库代理对象
     *
     * @param context           上下文
     * @param proxy             代理接口class
     * @param retrofitInterface retrofit 接口class
     * @param <P>               代理接口class泛型
     * @param <V>               retrofit 接口class泛型
     * @return
     */
    public static <P, V> P getRepositoryProxy(Context context, Class<P> proxy,
                                              Class<V> retrofitInterface,
                                              Object repositoryImpl) {
        return (P) Proxy.newProxyInstance(proxy.getClassLoader(),
                new Class<?>[]{proxy},
                new RepositoryInvocationHandler<>(context, retrofitInterface,repositoryImpl));
    }

    public static <P> P getPresenterProxy(P proxyImpl) {
        return (P) Proxy.newProxyInstance(proxyImpl.getClass().getClassLoader(),
                proxyImpl.getClass().getInterfaces(), new PresenterInvocationHandler(proxyImpl));
    }
}
