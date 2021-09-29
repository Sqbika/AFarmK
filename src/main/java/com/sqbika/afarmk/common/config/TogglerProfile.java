package com.sqbika.afarmk.common.config;

import com.google.gson.annotations.Expose;
import net.minecraft.client.option.KeyBinding;

public class TogglerProfile {

    @Expose
    public String name = "Temporary Profile";
    @Expose
    public String[] buttons = new String[] {
            "SHIFT", "LEFT_CLICK", "RIGHT_CLICK", "FORWARD", "BACKWARD", "LEFT", "RIGHT", "JUMP"
    };

    @Expose
    public int profileKeybind = 0;

    public KeyBinding keyBinding;

    public void setKeyBinding(KeyBinding keybind) {
        this.keyBinding = keybind;
    }
}
