package com.sqbika.afarmk.common.enums;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.interfaces.GetKeyBindFromGameSettings;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public enum BUTTON_TOGGLES {
    SHIFT("toggle.shift", Keyboard.KEY_NUMPAD1, settings -> settings.keyBindSneak),
    LEFT_CLICK("toggle.leftClick", Keyboard.KEY_NUMPAD2, settings -> settings.keyBindAttack),
    RIGHT_CLICK("toggle.rightClick", Keyboard.KEY_NUMPAD3, settings -> settings.keyBindUseItem),
    FORWARD("toggle.forward", Keyboard.KEY_NUMPAD8, settings -> settings.keyBindForward),
    BACKWARD("toggle.backward", Keyboard.KEY_NUMPAD5, settings -> settings.keyBindBack),
    LEFT("toggle.left", Keyboard.KEY_NUMPAD5, settings -> settings.keyBindLeft),
    RIGHT("toggle.right", Keyboard.KEY_NUMPAD6, settings -> settings.keyBindRight),
    JUMP("toggle.jump", Keyboard.KEY_NUMPAD0, settings -> settings.keyBindJump)
    ;

    private String trKey;
    private int kbButton;
    private GetKeyBindFromGameSettings gkbfs;
    private KeyBinding keyBinding;

    BUTTON_TOGGLES(String trKey, int kbButton, GetKeyBindFromGameSettings gkbfs) {
        this.trKey = trKey;
        this.kbButton = kbButton;
        this.gkbfs = gkbfs;
        this.keyBinding = new KeyBinding(trKey, kbButton, Constants.TOGGLER_CATEGORY);
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
            if (bt.keyBinding.getKeyCode() == eventKey)
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
