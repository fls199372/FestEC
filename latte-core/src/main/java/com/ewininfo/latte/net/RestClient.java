package com.ewininfo.latte.net;

import android.content.Context;

import com.ewininfo.latte.net.callback.IError;
import com.ewininfo.latte.net.callback.IFailure;
import com.ewininfo.latte.net.callback.IRequest;
import com.ewininfo.latte.net.callback.ISuccess;
import com.ewininfo.latte.net.callback.RequestCallbacks;
import com.ewininfo.latte.ui.LatteLoader;
import com.ewininfo.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by fulishuang on 2017/8/3.
 * 网络框架
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();


    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.CONTEXT = context;
    }

    /**
     * 构造者
     */
    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        //判断REQUEST 不为空！
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        if(LOADER_STYLE!=null){
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);

        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case UPLOAD:
                /** final RequestBody requestBody =
                 RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                 final MultipartBody.Part body =
                 MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                 call = service.upload(URL, body);   */
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    /**
     * 单独方法   方便多处使用
     *
     * @return
     */
    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
