package com.ewininfo.latte.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by fulishuang on 2017/8/2.
 */

public final class Latte {
    //返回我们的配置
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),
                context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     *  其他相应的配置
     * @return
     */
    public static HashMap<String,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }

    /**
     * 获取Context类
     * @return
     */
    public static Context getApplication(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

}
