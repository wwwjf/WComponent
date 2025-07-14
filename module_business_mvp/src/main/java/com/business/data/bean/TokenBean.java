package com.business.data.bean;

import java.io.Serializable;

public class TokenBean implements Serializable {

    private static final long serialVersionUID = 3053512425072321752L;


    /**
     * epayAccessKey : 0B4EE0C6CC4649F68DE9E2940A346ACE
     * customer_info : null
     */

    private String epayAccessKey;
    private String randomkey;
    private CustomerBean customer_info;

    public String getEpayAccessKey() {
        return epayAccessKey;
    }

    public void setEpayAccessKey(String epayAccessKey) {
        this.epayAccessKey = epayAccessKey;
    }

    public String getRandomkey() {
        return randomkey;
    }

    public void setRandomkey(String randomkey) {
        this.randomkey = randomkey;
    }

    public CustomerBean getCustomer_info() {
        return customer_info;
    }

    public void setCustomer_info(CustomerBean customer_info) {
        this.customer_info = customer_info;
    }
}
