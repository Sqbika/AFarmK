package com.sqbika.afarmk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static KeyBinding[] keys;

    public static void init() {

        keys = new KeyBinding[]{
                createKeyBind("toggle.shift", Keyboard.KEY_NUMPAD1),
                createKeyBind("toggle.leftClick", Keyboard.KEY_NUMPAD2), //Left Click
                createKeyBind("toggle.rightClick", Keyboard.KEY_NUMPAD3),
                createKeyBind("toggle.forward", Keyboard.KEY_NUMPAD8),
                createKeyBind("toggle.backwards", Keyboard.KEY_NUMPAD5),
                createKeyBind("toggle.left", Keyboard.KEY_NUMPAD4),
                createKeyBind("toggle.right", Keyboard.KEY_NUMPAD6),
                createKeyBind("toggle.jump", Keyboard.KEY_NUMPAD0),
                createKeyBind("toggle.reset", Keyboard.KEY_NUMPADENTER)
       };
        for (KeyBinding key : keys) {
            ClientRegistry.registerKeyBinding(key);
        }
    }

    private static KeyBinding createKeyBind(String name, int keybind) {
        return new KeyBinding(name, keybind, "afarmk.toggles");
    }

    public static KeyBinding find(int keybind) {
        for (KeyBinding key : keys) {
            if (key.getKeyCode() == keybind)
                return key;
        }
        return null;
    }
}
