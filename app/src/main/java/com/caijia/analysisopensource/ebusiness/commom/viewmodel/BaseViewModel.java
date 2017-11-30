package com.caijia.analysisopensource.ebusiness.commom.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.caijia.analysisopensource.ebusiness.commom.http.ThreadSwitchHelper;

import java.util.concurrent.Callable;

/**
 * Created by cai.jia on 2017/11/24.
 */

public class BaseViewModel<T> extends AndroidViewModel {

    protected MutableLiveData<T> data;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        data = new MutableLiveData<>();
    }

    protected void loadData(Callable<T> callable) {
        new ThreadSwitchHelper<T>()
                .task(callable)
                .execute(new ThreadSwitchHelper.Callback<T>() {

                    @Override
                    public void onSuccess(T result) {
                        data.setValue(result);
                    }

                    @Override
                    public void onFailure(String error) {
                        data.setValue(null);
                    }
                });
    }
}
