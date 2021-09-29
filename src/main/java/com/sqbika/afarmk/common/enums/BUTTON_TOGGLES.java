package com.sqbika.afarmk.common.enums;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.StaticUtil;
import com.sqbika.afarmk.common.interfaces.GetKeyBindFromGameSettings;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;

public enum BUTTON_TOGGLES {
    SHIFT("toggle.shift", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 1), settings -> settings.keySneak),
    LEFT_CLICK("toggle.leftclick", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 2), settings -> settings.keyAttack),
    RIGHT_CLICK("toggle.rightclick", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 3), settings -> settings.keyUse),
    FORWARD("toggle.forward", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 8), settings -> settings.keyForward),
    BACKWARD("toggle.backward", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 5), settings -> settings.keyBack),
    LEFT("toggle.left", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 4), settings -> settings.keyLeft),
    RIGHT("toggle.right", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 6), settings -> settings.keyRight),
    JUMP("toggle.jump", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 0), settings -> settings.keyJump)
    ;

    private String trKey;
    private int kbButton;
    private GetKeyBindFromGameSettings gkbfs;
    private KeyBinding keyBinding;

    BUTTON_TOGGLES(String keybindId, int buttonKey, GetKeyBindFromGameSettings gkbfs) {
        this.trKey = keybindId;
        this.kbButton = buttonKey;
        this.gkbfs = gkbfs;
        this.keyBinding = new KeyBinding(Constants.KEY_PREFIX + "." + keybindId, InputUtil.Type.KEYSYM, buttonKey, Constants.TOGGLER_CATEGORY);
    }

    public String getTrKey() {
        return trKey;
    }

    public static BUTTON_TOGGLES get(String trKey) {
        for (BUTTON_TOGGLES bt : values()) {
            if (bt.getTrKey().equals(trKey)) return bt;
        }
        return null;
    }

    public static BUTTON_TOGGLES get(int idx) {
        return values()[idx];
    }

    public static BUTTON_TOGGLES findByKeyBind(int eventKey) {
        for (BUTTON_TOGGLES bt: values()) {
            if (bt.keyBinding.matchesKey(InputUtil.Type.KEYSYM.ordinal(), eventKey))
                return bt;
        }
        return null;
    }

    public KeyBinding getToggleKeybind(GameOptions sett) {
        return this.gkbfs.doCallback(sett);
    }

    public int getKbButton() {
        return kbButton;
    }

    public KeyBinding getKeybinding() {
        return keyBinding;
    }

    public static void registerKeybinds() {
        for (BUTTON_TOGGLES value : values()) {
            KeyBindingHelper.registerKeyBinding(value.keyBinding);
        }
    }
}
