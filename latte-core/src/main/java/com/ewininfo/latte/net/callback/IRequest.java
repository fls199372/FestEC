package com.ewininfo.latte.net.callback;

/**
 * Created by fulishuang on 2017/8/3.
 * 请求时加载圈的处理
 */

public interface IRequest {

    void onRequestStart();

    void onRequestEnd();
}