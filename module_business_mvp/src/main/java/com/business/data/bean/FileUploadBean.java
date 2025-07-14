package com.business.data.bean;

import java.io.Serializable;

public class FileUploadBean implements Serializable {

    private static final long serialVersionUID = 703044308557242327L;

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
