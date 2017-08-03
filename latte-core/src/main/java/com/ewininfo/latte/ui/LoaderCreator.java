package com.ewininfo.latte.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by fulishuang on 2017/8/3.
 *反射机制封装
 */

public class LoaderCreator {
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    /**
     * 创建一个静态方法
     *
     * @param type
     * @param context
     * @return
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        //判断是否为空 不为空就实例化
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    /**
     * 创建一个私有的方法
     * @param name
     * @return
     */
    private static Indicator getIndicator(String name) {
        if(name==null||name.isEmpty()){
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        //判断如果有点 那么传入的就是类名
        if (!name.contains(".")) {
            //得到名字
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();

            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        //把名字生成一个具体的View
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
