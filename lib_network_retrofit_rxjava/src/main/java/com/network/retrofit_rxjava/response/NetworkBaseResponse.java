package com.network.retrofit_rxjava.response;

import java.io.Serial;
import java.io.Serializable;

public class NetworkBaseResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1388960990022176971L;


    /**
     * status : 0
     * info : 短信验证码不正确
     * data : []
     * api_version : 1.2.0
     */
    /**
     * 0失败，1成功
     */
    private int status;
    private String info;
    private String api_version;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }




}
