package com.network.retrofit_rxjava_two;

import com.common.log.KLog;
import com.common.utils.StringUtil;
import com.google.gson.JsonElement;
import com.network.retrofit_rxjava_two.constant.ApiCode;
import com.network.retrofit_rxjava_two.error.NetworkErrorHandler;
import com.network.retrofit_rxjava_two.error.RequestError5;
import com.network.retrofit_rxjava_two.helper.GsonHelper;
import com.network.retrofit_rxjava_two.helper.InterceptorHelper;
import com.network.retrofit_rxjava_two.helper.RxTransformerHelper;
import com.network.retrofit_rxjava_two.response.BaseResponse5;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class NetworkRequest {

    private static volatile NetworkRequest mNetworkRequest;

    private NetworkRequest() {

    }

    public static NetworkRequest getInstance() {
        if (mNetworkRequest == null) {
            synchronized (NetworkRequest.class) {
                if (mNetworkRequest == null) {
                    mNetworkRequest = new NetworkRequest();
                }
            }
        }
        return mNetworkRequest;
    }

    // ------------------------  提供外部方法调用 开始-----------------

    public <T> Observable<BaseResponse5<T>> getFieldMap5(String url, Map<String, Object> params, Type type) {
        return getRequest(url, params)
                .flatMap(jsonElement -> transformResponse5(jsonElement, type));
    }

    public <T> Observable<BaseResponse5<T>> postFieldMap5(String url, Map<String, Object> params, Type type) {

        return postFieldRequest(url, params)
                .flatMap(jsonElement -> transformResponse5(jsonElement, type));
    }

    public <T> Observable<BaseResponse5<T>> postMultipart5(String url, String fileKey, File file, Type type) {

        MultipartBody.Part fileMultipartBody = parseMultipartBody(fileKey, file);

        return requestMultipart(url, fileMultipartBody)
                .flatMap(jsonElement -> transformResponse5(jsonElement, type));
    }

    public <T> Observable<BaseResponse5<T>> postRequestBody(String url, String fileKey, File file, Map<String, Object> params, Type type) {

        RequestBody requestBody = null;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), StringUtil.valueOf(entry.getValue()));
        }
        String encodeFileName = "";
        try {
            encodeFileName = URLEncoder.encode(file.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }
        builder.addFormDataPart(fileKey, encodeFileName, RequestBody.create(MediaType.parse("multipart/form-data"), file));
        requestBody = builder.build();
        return postRequestBody(url, requestBody)
                .flatMap(jsonElement -> transformResponse5(jsonElement, type));
    }


    /**
     * 下载文件
     *
     * @param url 文件路径
     */
    public Observable<Response<ResponseBody>> getDownloadByFullUrl(String url) {
        return getApiStore().getDownloadByFullUrl(url)
                .compose(RxTransformerHelper.observableIO2Main());
    }

    /**
     * 下载文件
     *
     * @param url 文件路径
     */
    public Observable<Response<ResponseBody>> postDownloadByFullUrl(String url, Map<String, Object> params) {

        MultipartBody.Builder builder = new MultipartBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), StringUtil.valueOf(entry.getValue()));
        }
        return getApiStore().postDownloadByFullUrl(url, builder.build())
                .compose(RxTransformerHelper.observableIO2Main());
    }


    // ------------------------  提供外部方法调用 结束-----------------


    private Observable<JsonElement> requestMultipart(String url, MultipartBody.Part fileBody) {


        Observable<JsonElement> observable;
        if (isFullUrl(url)) {
            observable = getApiStore().postMultipartByFullUrl(url, fileBody);
        } else {
            observable = getApiStore().postMultipart(url, fileBody);
        }

        return observable.compose(RxTransformerHelper.observableIO2Main())
                .onErrorResumeNext(new NetworkErrorHandler<>());
    }

    private Observable<JsonElement> postRequestBody(String url, RequestBody requestBody) {
        return getApiStore().postRequestBodyByFullUrl(url, requestBody)
                .compose(RxTransformerHelper.observableIO2Main())
                .onErrorResumeNext(new NetworkErrorHandler<>());
    }


    private MultipartBody.Part parseMultipartBody(String fileKey, File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        String encodeFileName = "";
        try {
            encodeFileName = URLEncoder.encode(file.getName(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }

        return MultipartBody.Part.createFormData(fileKey, encodeFileName, requestFile);
    }


    private Observable<JsonElement> postFieldRequest(String url, Map<String, Object> params) {
        Observable<JsonElement> observable = null;

        if (isFullUrl(url)) {

            observable = getApiStore().postFieldMapByFullUrl(url, params);

        } else {

            observable = getApiStore().postFieldMap(url, params);

        }
        return observable.compose(RxTransformerHelper.observableIO2Main())
                .onErrorResumeNext(new NetworkErrorHandler<>());
    }

    private Observable<JsonElement> postRequest(String url, Map<String, Object> params) {

        Observable<JsonElement> observable;

        if (isFullUrl(url)) {

            observable = getApiStore().postMapByFullUrl(url, params);

        } else {

            observable = getApiStore().postMap(url, params);

        }
        return observable.compose(RxTransformerHelper.observableIO2Main())
                .onErrorResumeNext(new NetworkErrorHandler<>());
    }

    private Observable<JsonElement> getRequest(String url, Map<String, Object> params) {
        Observable<JsonElement> ov;

        if (isFullUrl(url)) {

            ov = getApiStore().getByFullUrl(url, params, InterceptorHelper.getHeaderMap());

        } else {

            ov = getApiStore().get(url, params, InterceptorHelper.getHeaderMap());

        }

        return ov.compose(RxTransformerHelper.observableIO2Main())
                .onErrorResumeNext(new NetworkErrorHandler<>());

    }

    private <T> T parseJsonElement(JsonElement json, Type type) {
        return GsonHelper.getsGson().fromJson(json, type);
    }

    private boolean isFullUrl(String url) {
        return url.toLowerCase().contains("http") || url.toLowerCase().contains("https");
    }

    private ApiService5 getApiStore() {
        return RetrofitClient.getInstance().getAPIService();
    }


    private <T> Observable<BaseResponse5<T>> transformResponse5(JsonElement jsonElement, Type type) {
        BaseResponse5<T> baseResponse = GsonHelper.getsGson().fromJson(jsonElement, type);
        int code = baseResponse.getCode();
        Observable<BaseResponse5<T>> result = null;
        switch (code) {
            case ApiCode.CODE_SUCCESS_1:
                result = Observable.just(baseResponse);
                break;
            case ApiCode.CODE_10040:
            case ApiCode.CODE_10041:
            case ApiCode.CODE_10042:
                //清除本地登录信息，跳转登录页面
                KLog.e("---------登录标志位置为false");
                /*UserInfoManager.exitUser();
                result = Observable.error(new RequestError5(code, String.valueOf(baseResponse.getMsg())));
                String topActivityPackageName = IntentUtil.getTopActivityName();
                if (!StringUtil.equals(MainActivity.class.getName(), topActivityPackageName) &&
                        !StringUtil.equals(SplashActivity.class.getName(), topActivityPackageName)) {
                    KLog.e("___" + topActivityPackageName);
                        *//*Intent intent = new Intent();
                        IntentUtil.startActivityForResult(ActivityUtils.getTopActivity(),intent, EPayConstant.ActivityCode.RESULT_LOGIN_CODE_2);*//*
                    IntentUtil.startActivity(LoginActivity.class);
                }*/
                break;
            case ApiCode.CODE_10186:
                KLog.e("=====code=" + code + ",message=" + baseResponse.getMsg());
                /*Bundle bundle = new Bundle();
                bundle.putString(EPayConstant.IntentKey.PARAMS_1, baseResponse.getMsg());
                IntentUtil.startActivityWithMSg(VersionActivity.class, bundle);
                result = Observable.error(new RequestError5(code, ""));*/
                break;
            default:
                KLog.e("=====code=" + code + ",message=" + baseResponse.getMsg());
                result = Observable.error(new RequestError5(code, String.valueOf(baseResponse.getMsg())));
                break;
        }

        return result;

    }


}
