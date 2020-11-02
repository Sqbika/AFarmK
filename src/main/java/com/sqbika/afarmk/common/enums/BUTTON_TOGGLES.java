package com.sqbika.afarmk.common.enums;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.StaticUtil;
import com.sqbika.afarmk.common.interfaces.GetKeyBindFromGameSettings;
import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;

public enum BUTTON_TOGGLES {
    SHIFT("key.afarmk.toggle.shift", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 1), settings -> settings.keyBindSneak),
    LEFT_CLICK("key.afarmk.toggle.leftclick", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 2), settings -> settings.keyBindAttack),
    RIGHT_CLICK("key.afarmk.toggle.rightclick", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 3), settings -> settings.keyBindUseItem),
    FORWARD("key.afarmk.toggle.forward", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 8), settings -> settings.keyBindForward),
    BACKWARD("key.afarmk.toggle.backward", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 5), settings -> settings.keyBindBack),
    LEFT("key.afarmk.toggle.left", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 4), settings -> settings.keyBindLeft),
    RIGHT("key.afarmk.toggle.right", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 6), settings -> settings.keyBindRight),
    JUMP("key.afarmk.toggle.jump", StaticUtil.getKeyCode(Constants.NUMPAD_PREFIX + 0), settings -> settings.keyBindJump)
    ;

    private String trKey;
    private int kbButton;
    private GetKeyBindFromGameSettings gkbfs;
    private KeyBinding keyBinding;

    BUTTON_TOGGLES(String keybindId, int buttonKey, GetKeyBindFromGameSettings gkbfs) {
        this.trKey = keybindId;
        this.kbButton = buttonKey;
        this.gkbfs = gkbfs;
        this.keyBinding = new KeyBinding(keybindId, InputMappings.Type.KEYSYM, buttonKey, Constants.TOGGLER_CATEGORY);
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
            if (bt.keyBinding.matchesKey(InputMappings.Type.KEYSYM.ordinal(), eventKey))
                return bt;
        }
        return null;
    }

    public KeyBinding getToggleKeybind(GameSettings sett) {
        return this.gkbfs.doCallback(sett);
    }

    public int getKbButton() {
        return kbButton;
    }

    public KeyBinding getKeybinding() {
        return keyBinding;
    }
}
