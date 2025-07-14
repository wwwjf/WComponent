package com.network.retrofit_rxjava_two.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse5<T> implements Serializable {
    private static final long serialVersionUID = -3422729770565427092L;


    /**
     * code : 1
     * msg : 操作成功
     * data : {}
     */

    @SerializedName(value = "code")
    private int code;
    @SerializedName(value = "message")
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
