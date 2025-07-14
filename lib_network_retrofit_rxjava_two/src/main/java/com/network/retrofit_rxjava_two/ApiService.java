package com.network.retrofit_rxjava_two;

import com.google.gson.JsonElement;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {


    @FormUrlEncoded
    @POST("{url}")
    Observable<JsonElement> postFieldMap(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, Object> params);

    @FormUrlEncoded
    @POST()
    Observable<JsonElement> postFieldMapByFullUrl(@Url String url, @FieldMap Map<String, Object> params);

    // ------------------------  netWorkApi start -----------------
    @POST("{sourceUrl}")
    Observable<JsonElement> postMap(@Path(value = "sourceUrl", encoded = true) String sourceUrl,
                                    @QueryMap Map<String, Object> params);


    @POST()
    Observable<JsonElement> postMapByFullUrl(@Url String url,
                                             @QueryMap Map<String, Object> params);

    @GET("{sourceUrl}")
    Observable<JsonElement> get(@Path(value = "sourceUrl", encoded = true) String sourceUrl,
                                @QueryMap Map<String, Object> params);

    @GET()
    Observable<JsonElement> getByFullUrl(@Url String url,
                                         @QueryMap Map<String, Object> params);

    @GET("{sourceUrl}")
    Observable<Response<ResponseBody>> getDownload(@Path(value = "sourceUrl",encoded = true) String path);

    @GET()
    @Headers({"Accept-Encoding:identity"})
    Observable<Response<ResponseBody>> getDownloadByFullUrl(@Url String url);


    @Multipart
    @POST()
    Observable<JsonElement> postMultipart(@Url String path, @Part MultipartBody.Part file);


    @Multipart
    @POST("{sourceUrl}")
    Observable<JsonElement> postMultipartByFullUrl(@Path(value = "sourceUrl", encoded = true) String sourceUrl, @Part MultipartBody.Part file);


    // ------------------------  netWorkApi end -----------------
}
