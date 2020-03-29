package com.sqbika.afarmk.common;

import net.minecraft.client.util.InputMappings;

public class StaticUtil {

    public static int getKeyCode(String keyPrefix) {
        InputMappings.Input keybind = InputMappings.getInputByName(keyPrefix);
        return keybind.getKeyCode();
    }
}
