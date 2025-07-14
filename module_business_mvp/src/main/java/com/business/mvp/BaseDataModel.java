package com.business.mvp;

import com.business.constant.ApiPath;
import com.business.constant.BusinessConstant;
import com.business.data.bean.FileUploadBean;
import com.business.data.bean.TokenBean;
import com.business.data.bean.VersionMsgBean;
import com.business.data.requests.LoginRequest;
import com.common.log.KLog;
import com.common.manager.CacheDataManager;
import com.common.utils.StringUtil;
import com.google.gson.reflect.TypeToken;
import com.network.retrofit_rxjava_two.NetworkRequest;
import com.network.retrofit_rxjava_two.constant.Api;
import com.network.retrofit_rxjava_two.constant.ApiCode;
import com.network.retrofit_rxjava_two.error.RequestError5;
import com.network.retrofit_rxjava_two.response.BaseResponse5;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class BaseDataModel {


    /**
     * 检测版本
     */
    public Observable<VersionMsgBean> checkVersion() {
        return NetworkRequest.getInstance()
                .getFieldMap5(ApiPath.PATH_CHECK_VERSION,
                        new HashMap<>(),
                        new TypeToken<BaseResponse5<VersionMsgBean>>() {
                        }.getType())
                .flatMap(response -> Observable.just((VersionMsgBean) response.getData()));
    }

    /**
     * 登录
     *
     * @param request 参数
     */
    public Observable<BaseResponse5<TokenBean>> login(LoginRequest request) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userName", StringUtil.valueOf(request.getUsername()));
        params.put("password", StringUtil.valueOf(request.getPassword()));

        return NetworkRequest.getInstance()
                .postFieldMap5(ApiPath.PATH_LOGIN,
                        params, new TypeToken<BaseResponse5<TokenBean>>() {
                        }.getType());
    }


    /**
     * 单文件上传
     *
     * @param path 本地文件路径
     */
    public Observable<FileUploadBean> uploadFile5(String path) {
        if (StringUtil.isTrimEmpty(path)) {
            return Observable.error(new RequestError5(ApiCode.CODE_10001, "本地文件路径为空"));
        }
        String size = CacheDataManager.byte2FitMemorySize(new File(path).length());
        KLog.e("size=" + size);

        File file = new File(path);
        return NetworkRequest.getInstance()
                .postMultipart5(Api.BASE_URL + ApiPath.PATH_FILE_UPLOAD,
                        "fileAttr", file,
                        new TypeToken<BaseResponse5<FileUploadBean>>() {
                        }.getType())
                .flatMap(response -> Observable.just((FileUploadBean) response.getData()));
    }
    /**
     * 单文件上传（未登录）
     *
     * @param path 本地文件路径
     */
    public Observable<FileUploadBean> uploadFile5UnLogin(String path,String unBindGAToken) {
        if (StringUtil.isTrimEmpty(path)) {
            return Observable.error(new RequestError5(ApiCode.CODE_10001, "本地文件路径为空"));
        }
        String size = CacheDataManager.byte2FitMemorySize(new File(path).length());
        KLog.e("size=" + size);

        File file = new File(path);
        Map<String,Object> params = new HashMap<>();
        params.put("unBindGAToken",unBindGAToken);
        return NetworkRequest.getInstance()
                .postRequestBody(Api.BASE_URL + ApiPath.PATH_FILE_UPLOAD_UNLOGIN,
                        "fileAttr", file, params,
                        new TypeToken<BaseResponse5<FileUploadBean>>() {
                        }.getType())
                .flatMap(response -> Observable.just((FileUploadBean) response.getData()));
    }

    public Observable<Response<ResponseBody>> downloadFile(String url) {
        return NetworkRequest.getInstance().getDownloadByFullUrl(url);
    }

    public Observable<Response<ResponseBody>> downloadPostFile(String url, String orderNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("orderNo", StringUtil.valueOf(orderNo));
        return NetworkRequest.getInstance().postDownloadByFullUrl(url, params);
    }
}
