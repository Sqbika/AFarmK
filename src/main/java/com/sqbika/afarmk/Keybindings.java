package com.sqbika.afarmk;

import com.sqbika.afarmk.Togglers.AFarmKConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static KeyBinding[] keys;

    public static KeyBinding[] specials;

    public static void init() {

        //Toggleable keys
        keys = new KeyBinding[]{
                createKeyBind("toggle.shift", Keyboard.KEY_NUMPAD1),
                createKeyBind("toggle.leftClick", Keyboard.KEY_NUMPAD2), //Left Click
                createKeyBind("toggle.rightClick", Keyboard.KEY_NUMPAD3),
                createKeyBind("toggle.forward", Keyboard.KEY_NUMPAD8),
                createKeyBind("toggle.backwards", Keyboard.KEY_NUMPAD5),
                createKeyBind("toggle.left", Keyboard.KEY_NUMPAD4),
                createKeyBind("toggle.right", Keyboard.KEY_NUMPAD6),
                createKeyBind("toggle.jump", Keyboard.KEY_NUMPAD0),
        };

        //Special Keys
        specials = new KeyBinding[]{
                createKeyBind("toggle.reset", Keyboard.KEY_NUMPADENTER),
                createProfileKeyBind(1, Keyboard.KEY_NUMPAD1),
                createProfileKeyBind(2, Keyboard.KEY_NUMPAD2),
                createProfileKeyBind(3, Keyboard.KEY_NUMPAD3),
                createProfileKeyBind(4, Keyboard.KEY_NUMPAD4),
                createProfileKeyBind(5, Keyboard.KEY_NUMPAD5),
                createProfileKeyBind(6, Keyboard.KEY_NUMPAD6),
                createProfileKeyBind(7, Keyboard.KEY_NUMPAD7),
                createProfileKeyBind(8, Keyboard.KEY_NUMPAD8),
                createProfileKeyBind(9, Keyboard.KEY_NUMPAD9),
        };

        for (KeyBinding key : keys) {
            if(AFarmKConfig.enableIndividual)
                ClientRegistry.registerKeyBinding(key);
        }
        for (KeyBinding key : specials) {
            if (key == null) return;
            if (key.getDisplayName().startsWith("toggle.profile"))
                if (!AFarmKConfig.enableProfiles)
                    return;
            ClientRegistry.registerKeyBinding(key);
        }
    }

    private static KeyBinding createKeyBind(String name, int keybind) {
        return new KeyBinding(name, keybind, "afarmk.toggles");
    }

    private static KeyBinding createProfileKeyBind(int id, int keybind) {
        if (AFarmKConfig.get(id).enabled) {
            KeyBinding key = new KeyBinding("toggle.profile" + id, keybind, "afarmk.profileTogglers");
            key.setKeyModifierAndCode(KeyModifier.ALT, keybind);
            return key;
        } else
            return null;
    }

    public static KeyBinding find(int keybind) {
        for (KeyBinding key : keys) {
            if (key.getKeyCode() == keybind)
                return key;
        }
        return null;
    }

    public static KeyBinding findSpecial(int keybind) {
        for (KeyBinding key : specials) {
            if (key.getKeyCode() == keybind) {
                return key;
            }
        }
        return null;
    }
}
