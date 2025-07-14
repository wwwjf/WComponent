package com.business.data.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {
    private static final long serialVersionUID = 6171308616882496206L;

    /**
     * uid : P201612987
     * email : Eric@epay.com
     * surName : null
     * givName : null
     * nickName : eric123
     * gender : 1
     * birthday : 1990-12-01T00:00:00.000+0000
     * imagePath : 8c376dbf-ff36-4772-b9d1-f67e18e97e00
     * countryCode :
     * level : 1
     * type : 1
     * certificatesStatus : 0
     * status : 0
     * defaultCurrency : USD
     * defaultLanguage : ZH_CN
     * backupEmail :
     * googleCodeClosed : 0
     * exchangerStatus : 0
     * apiStatus : 1
     * reqIp : 192.168.2.8
     */

    private String uid;
    private String email;
    private String surName;
    private String givName;
    private String nickName;
    private int gender;
    private String birthday;
    private String imagePath;
    private String countryCode;
    private int level;
    private int type;
    private int certificatesStatus;
    private int status;
    private String defaultCurrency;
    private String defaultLanguage;
    private String backupEmail;
    private int googleCodeClosed;
    private int exchangerStatus;
    private int apiStatus;
    private String reqIp;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGivName() {
        return givName;
    }

    public void setGivName(String givName) {
        this.givName = givName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCertificatesStatus() {
        return certificatesStatus;
    }

    public void setCertificatesStatus(int certificatesStatus) {
        this.certificatesStatus = certificatesStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getBackupEmail() {
        return backupEmail;
    }

    public void setBackupEmail(String backupEmail) {
        this.backupEmail = backupEmail;
    }

    public int getGoogleCodeClosed() {
        return googleCodeClosed;
    }

    public void setGoogleCodeClosed(int googleCodeClosed) {
        this.googleCodeClosed = googleCodeClosed;
    }

    public int getExchangerStatus() {
        return exchangerStatus;
    }

    public void setExchangerStatus(int exchangerStatus) {
        this.exchangerStatus = exchangerStatus;
    }

    public int getApiStatus() {
        return apiStatus;
    }

    public void setApiStatus(int apiStatus) {
        this.apiStatus = apiStatus;
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp;
    }
}
