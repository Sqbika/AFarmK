package com.sqbika.afarmk.common;

import net.minecraft.client.util.InputUtil;

public class StaticUtil {

    public static int getKeyCode(String keyPrefix) {
        InputUtil.Key keybind = InputUtil.fromTranslationKey(keyPrefix);
        return keybind.getCode();
    }
}
