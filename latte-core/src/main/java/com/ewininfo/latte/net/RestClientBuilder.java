package com.ewininfo.latte.net;

import android.content.Context;

import com.ewininfo.latte.net.callback.IError;
import com.ewininfo.latte.net.callback.IFailure;
import com.ewininfo.latte.net.callback.IRequest;
import com.ewininfo.latte.net.callback.ISuccess;
import com.ewininfo.latte.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by fulishuang on 2017/8/3.
 * 建造者类
 */

public class RestClientBuilder {
    private String mUrl = null;
    /**
     * WeakHashMap 可以使内存更精确一些  不用的时候可以回收
     */
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private Context mContext = null;
    /**
     * 不允许别的类直接NEW
     */
    RestClientBuilder(){

    }

    /**
     * 网址
     * @param url
     * @return
     */
    public final RestClientBuilder url(String url){
        this.mUrl=url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params){
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key,String value){

        PARAMS.put(key,value);
        return  this;
    }
    /**
     * 设置传值的参数的编码风格
     * @param raw
     * @return
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     *成功 回调处理
     */
    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess=iSuccess;
        return  this;
    }

    /**
     * 失败回调
     * @param iFailure
     * @return
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    /**
     * 错误回调
     * @param iError
     * @return
     */
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }
    /**
     * 检查 不允许空的 Map
     * @return
     */
    private Map<String,Object> checkParames(){
        if(PARAMS==null){
            return  new WeakHashMap<>();
        }
        return PARAMS;
    }

    /**
     * loader的方法  自定义
     * @param context
     * @param style
     * @return
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }
    /**
     * loader的方法  默认
     * @param context
     * @param
     * @return
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }
    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mISuccess,mIFailure,mIError,mBody,mLoaderStyle,mContext);

    }
}
