package com.ewininfo.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.ewininfo.latte.app.Latte;

/**
 * Created by fulishuang on 2017/8/3.
 * 测量工具类
 */

public class DimenUtil {
    //得到我们屏幕的宽
    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 得到我们屏幕的高
     * @return
     */
    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
