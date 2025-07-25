package com.common.eventbus.event;

public class MessageEvent {

    private int code;
    private String message;

    private Object object;

    public MessageEvent() {

    }

    public MessageEvent(int code) {
        this.code = code;
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(int code, Object object) {
        this.code = code;
        this.object = object;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
