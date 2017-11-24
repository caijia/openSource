package com.caijia.analysisopensource.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * Created by cai.jia on 2017/10/26 0026.
 */

public class PresenterInvocationHandler implements InvocationHandler {

    private Object receiver;

    public PresenterInvocationHandler(Object receiver) {
        this.receiver = receiver;
    }

    @Override
    public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
        int argLength = args.length;
        ComponentLifecycle lifecycle = null;
        if (argLength > 0 && args[0] instanceof ComponentLifecycle) {
            lifecycle = (ComponentLifecycle) args[0];
        }

        if (lifecycle == null || !lifecycle.canUse()) {
            return null;
        }

        Callable callable = () -> method.invoke(receiver, args);

        //view callback
        Object viewCallback = null;
        Method callbackMethod = null;
        if (args.length > 0) {
            int lastIndex = argLength - 1;
            Class<?> callbackType = method.getParameterTypes()[lastIndex];
            callbackMethod = callbackType.getMethods()[0];
            viewCallback = args[lastIndex];
        }
        new ThreadSwitchHelper()
                .task(callable)
                .execute(viewCallback == null ? null : getCallback(viewCallback, callbackMethod, lifecycle));
        return null;
    }

    @NonNull
    private ThreadSwitchHelper.Callback getCallback(@NonNull final Object viewCallback,
                                                    Method callbackMethod, final ComponentLifecycle lifecycle) {
        return new ThreadSwitchHelper.Callback() {
            @Override
            public void onSuccess(Object result) {
                callback(result, viewCallback, callbackMethod, lifecycle);
            }

            @Override
            public void onFailure(String error) {
                callback(null, viewCallback, callbackMethod, lifecycle);
            }
        };
    }

    private void callback(@Nullable Object result, @NonNull Object viewCallback,
                          Method method, ComponentLifecycle lifecycle) {
        if (lifecycle == null || !lifecycle.canUse()) {
            return;
        }
        try {
            Method m = viewCallback.getClass().getMethod(method.getName(), method.getParameterTypes());
            m.invoke(viewCallback, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
