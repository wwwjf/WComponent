package com.network.retrofit_rxjava_two.error;

public class RequestError5 extends Throwable {
    private static final long serialVersionUID = -8833859027999285769L;

    private int code;
    private String message;

    public RequestError5(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}