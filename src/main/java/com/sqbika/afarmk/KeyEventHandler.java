package com.sqbika.afarmk;

import com.sqbika.afarmk.Togglers.AFarmKConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.util.*;

import static java.util.Objects.isNull;

public class KeyEventHandler {

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (!Minecraft.getMinecraft().inGameHasFocus) return;
        GameSettings settings = FMLClientHandler.instance().getClient().gameSettings;
        if (!Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
            KeyBinding key = Keybindings.find(Keyboard.getEventKey());
            if (!isNull(key) && key.isKeyDown()) {
                KeyBinding bind = getKeybindFromDesc(key.getKeyDescription(), settings);
                if (!isNull(bind)) {
                    KeyBinding.setKeyBindState(bind.getKeyCode(), !bind.isKeyDown());
                } else {
                    specialCases(key.getKeyDescription(), settings);
                }
            }
        } else {
            KeyBinding key = Keybindings.findSpecial(Keyboard.getEventKey());
            if (!isNull(key) && key.isKeyDown())
                specialCases(key.getKeyDescription(), settings);
        }
    }

    private static KeyBinding getKeybindFromDesc(String desc, GameSettings sett) {
        switch (desc) {
            case "toggle.shift": return sett.keyBindSneak;
            case "toggle.leftClick": return sett.keyBindAttack;
            case "toggle.rightClick": return sett.keyBindUseItem;
            case "toggle.forward": return sett.keyBindForward;
            case "toggle.backwards": return sett.keyBindBack;
            case "toggle.left": return sett.keyBindLeft;
            case "toggle.right": return sett.keyBindRight;
            case "toggle.jump": return sett.keyBindJump;
            default: return null;
        }
    }

    public static String getTogglerString(GameSettings sett) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < Keybindings.keys.length; i++) {
            KeyBinding asd = getKeybindFromDesc(Keybindings.keys[i].getKeyDescription(), sett);
            if (asd.isKeyDown() && !asd.isPressed()) {
                builder.append(" ");
                builder.append(i+1);
            }
        }
        return builder.toString();
    }

    private void specialCases(String desc, GameSettings sett) {
        if (desc.startsWith("toggle.profile")) {
            profileCases(desc, sett);
            return;
        }
        switch(desc) {
            case "toggle.reset":
                for (KeyBinding key : Keybindings.keys) {
                    KeyBinding bind = getKeybindFromDesc(key.getKeyDescription(), sett);
                    if (!isNull(bind))
                        KeyBinding.setKeyBindState(bind.getKeyCode(), false);
                }
                break;
        }
    }

    private void profileCases(String desc, GameSettings sett) {
        int profile_id = Integer.parseInt(desc.replace("toggle.profile", ""));
        AFarmKConfig.TogglerProfile profile = AFarmKConfig.get(profile_id);
        BitSet set = profile.getProfileState();
        LogHelper.chat(set.toString());
        for (int i = 0; i < set.length(); i++) {
            if (set.get(i)) {
                KeyBinding key = getKeybindFromDesc(Keybindings.keys[i].getKeyDescription(), sett);
                if (!isNull(key))
                    KeyBinding.setKeyBindState(key.getKeyCode(), !profile.on);
            }
        }
        profile.on = !profile.on;
    }
}
