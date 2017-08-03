package com.ewininfo.latte.net;

import com.ewininfo.latte.app.ConfigKeys;
import com.ewininfo.latte.app.ConfigType;
import com.ewininfo.latte.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by fulishuang on 2017/8/3.
 *
 */

public class RestCreator {

    /**
     * 参数容器
     */
    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }
    /**
     * 创建一个get方法 返回 RestService
     * @return
     */
    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }


    /**
     * 构建全局Retrofit客户端  静态内部类Holder
     */
    private static final class RetrofitHolder {

        private static final  String BASE_URL= (String) Latte.getConfigurations()
                .get(ConfigType.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }
    /**
     * OKHTTP的惰性初始化 也是静态内部类
     */
    private static final  class  OKHttpHolder{
        private static final int TIME_OUT=60;
//        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
//        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);
//
//        /**
//         * 添加拦截器 trofit自己是不支持缓存的。要做缓存用的是okhttp的功能，
//         * 主要利用的是拦截器。这里一定要看清楚okhtt添加拦截器有两种。看清楚啊，
//         * 很多时候这样的小的设置可能然我们浪费一天的时间的。有1.addInterceptor ,
//         * 和2.addNetworkInterceptor这两种。他们的区别简单的说下，不知道也没关系，
//         * addNetworkInterceptor添加的是网络拦截器，他会在在request和resposne是分别被调用一次，
//         * addinterceptor添加的是aplication拦截器，他只会在response被调用一次。
//         * @return
//         */
//        private static OkHttpClient.Builder addInterceptor() {
//            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
//                for (Interceptor interceptor : INTERCEPTORS) {
//                    BUILDER.addInterceptor(interceptor);
//                }
//            }
//            return BUILDER;
//        }
        /**
         * 建造者模式
         */
        private static final OkHttpClient OK_HTTP_CLIENT =new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
    /**
     * RestService 内部类
     */
    private static final class RestServiceHolder{
        private static  final  RestService REST_SERVICE=
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);

    }
}
