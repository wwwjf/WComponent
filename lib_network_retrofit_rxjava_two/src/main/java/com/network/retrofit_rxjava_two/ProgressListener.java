package com.network.retrofit_rxjava_two;

public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);

    /**
     * 下载完成
     * @param path 文件保存路径
     */
    void onFinish(String path);

    /**
     * 下载出错
     * @param errorMsg 错误信息
     */
    void onFailure(String errorMsg);
}