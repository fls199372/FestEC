package com.ewininfo.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by fulishuang on 2017/8/3.
 * 枚举类型
 */

public enum  EcIcons implements Icon {
    icon_ali_pay('\ue602'),
    icon_scan ('\ue606');

    private char character; //返回的类型

    /**
     * 构造方法
     * @param character
     */
    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
