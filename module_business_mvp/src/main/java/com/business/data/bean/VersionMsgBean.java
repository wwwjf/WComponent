package com.business.data.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VersionMsgBean implements Serializable {

    private static final long serialVersionUID = 26614498721132142L;
    /**
     * cnMsg :
     * enMsg : Found new version, is it updated?
     */

    private int vCode;

    private String vName;

    @SerializedName("cn")
    private String cnMsg;
    @SerializedName("en")
    private String enMsg;

    private int isForce;
    private int isShowContent;
    private String url;

    public int getvCode() {
        return vCode;
    }

    public void setvCode(int vCode) {
        this.vCode = vCode;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getCnMsg() {
        return cnMsg;
    }

    public void setCnMsg(String cnMsg) {
        this.cnMsg = cnMsg;
    }

    public String getEnMsg() {
        return enMsg;
    }

    public void setEnMsg(String enMsg) {
        this.enMsg = enMsg;
    }

    public int getIsForce() {
        return isForce;
    }

    public void setIsForce(int isForce) {
        this.isForce = isForce;
    }

    public int getIsShowContent() {
        return isShowContent;
    }

    public void setIsShowContent(int isShowContent) {
        this.isShowContent = isShowContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}