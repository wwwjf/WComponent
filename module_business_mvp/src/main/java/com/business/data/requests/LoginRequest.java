package com.business.data.requests;

import java.io.Serializable;

/**
 * 登录请求参数
 */
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 9207854851708237281L;


    /**
     * 登录方式
     */
    private int logintype;

    private String username;

    private String password;

    private String areacode;

    private String redId;

    private String flowno;

    private String promotId;

    /**
     */
    private String source;

    private String system;

    private String FAPLangLocale;

    private String FAPView;

    public int getLogintype() {
        return logintype;
    }

    public void setLogintype(int logintype) {
        this.logintype = logintype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getRedId() {
        return redId;
    }

    public void setRedId(String redId) {
        this.redId = redId;
    }

    public String getFlowno() {
        return flowno;
    }

    public void setFlowno(String flowno) {
        this.flowno = flowno;
    }

    public String getPromotId() {
        return promotId;
    }

    public void setPromotId(String promotId) {
        this.promotId = promotId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getFAPLangLocale() {
        return FAPLangLocale;
    }

    public void setFAPLangLocale(String FAPLangLocale) {
        this.FAPLangLocale = FAPLangLocale;
    }

    public String getFAPView() {
        return FAPView;
    }

    public void setFAPView(String FAPView) {
        this.FAPView = FAPView;
    }
}
