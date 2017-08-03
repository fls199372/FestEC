package com.ewininfo.festec.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ewininfo.latte.delegates.LatteDelegate;
import com.ewininfo.latte.net.RestClient;
import com.ewininfo.latte.net.callback.IError;
import com.ewininfo.latte.net.callback.IFailure;
import com.ewininfo.latte.net.callback.ISuccess;

/**
 * Created by fulishuang on 2017/8/3.
 */

public class ExampleDelegate extends LatteDelegate {
    /**
     * 布局文件
     * @return
     */
    @Override
    public Object setLayout() {
        return R.layout.activity_main;
    }

    //对控件进行的操作
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        testRestClient();
    }
    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
