package com.business.constant;

public interface ApiPath {

    String PATH_CHECK_VERSION = "apps/android/android.v";

    /**
     * 登录
     */
    String PATH_LOGIN = "customer/customer/login";


    /**
     * 文件上传
     */
    String PATH_FILE_UPLOAD = "file/upload/index";
    /**
     * 文件上传
     */
    String PATH_FILE_UPLOAD_UNLOGIN = "file/upload/uploadGAFile";

    /**
     * 上传成功的图片路径
     */
    String PATH_PICTURE_FILE = "file/download/stream?filePath=%s";

    /**
     * 上传成功的图片路径（未登录）
     */
    String PATH_PICTURE_FILE_UNLOGIN = "file/download/googleCodeFile?filePath=%s";


}
