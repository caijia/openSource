package com.caijia.analysisopensource.sample.service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cai.jia on 2017/11/21.
 */

public class Response<T> {

    private int code;

    private String errorMsg;

    @SerializedName("Data")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
