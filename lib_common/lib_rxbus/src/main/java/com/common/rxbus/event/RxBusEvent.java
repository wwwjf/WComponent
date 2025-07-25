package com.common.rxbus.event;

public class RxBusEvent {

    private int eventType = 0;
    private Object object;

    public RxBusEvent() {
    }

    public RxBusEvent(int eventType,Object object) {
        this.eventType = eventType;
        this.object = object;
    }
}
