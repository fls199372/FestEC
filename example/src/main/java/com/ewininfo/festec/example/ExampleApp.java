package com.ewininfo.festec.example;

import android.app.Application;

import com.ewininfo.latte.app.Latte;
import com.ewininfo.latte.ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by fulishuang on 2017/8/2.
 */

public class ExampleApp  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())//加入字体图标
                .withIcon(new FontEcModule())//加入自定义字体图标
                .withApiHost("http://127.0.0.1/")//网址
                .configure();
    }
}
