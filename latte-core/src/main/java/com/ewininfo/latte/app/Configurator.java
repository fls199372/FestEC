package com.ewininfo.latte.app;


import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * Created by fulishuang on 2017/8/2.
 *
 */

public class Configurator {
    //static final 声明或者初始化时大写 并且用下划线分割    代码规范
    private static final HashMap<String ,Object> LATTE_CONFIGS=new HashMap<>();
    //存储字体图标的空间
    private static final ArrayList<IconFontDescriptor> ICONS=new ArrayList<>();

    //.name()  枚举类中输出String
    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    /**
     * 线程安全的懒汉模式
     * @return
     */
    public static Configurator getInstance(){
        return  Holder.INSTANCE;
    }
    final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }
    /**
     * 静态内部类的初始化
     */
    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    /**
     * 告诉我们的配置文件已经配置好了
     */
    public final void configure(){
        initTcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    /**
     * 配置API
     * @param host
     * @return
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    /**
     * 检查配置项完成没有
     */
    private void checkConfiguration(){
        // 在我们写类变量 或者方法变量的时候  尽量让他的不可变性达到最大化   尽量使用final
        final boolean isReady= (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        //如果配置项没有完成 就运行  就抛出异常
        if(!isReady){
            throw new RuntimeException("Configuration is not ready call configure");
        }
    }

    /**
     * 获取配置 如果没有调用 configure  就会返回这个异常
     * @param key
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);

        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T)LATTE_CONFIGS.get(key);
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }
    /**
     * 初始化iconify
     */
    private void initTcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i=1;i<ICONS.size();i++){
                initializer.with(ICONS.get(i));
            }
        }else{

        }
    }
    /**
     * 加入自己的图标
     *
     * @param descriptor
     * @return
     */
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

}
