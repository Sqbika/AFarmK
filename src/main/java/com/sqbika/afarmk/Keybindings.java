package com.sqbika.afarmk;

import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import com.sqbika.afarmk.toggle.AFarmKConfig;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class Keybindings {

    public static KeyBinding[] specials;

    public static void init() {

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

        if (AFarmKConfig.enableIndividual) {
            for (BUTTON_TOGGLES key : BUTTON_TOGGLES.values()) {
                    ClientRegistry.registerKeyBinding(key.getKeybinding());
            }
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

    public static KeyBinding findSpecial(int keybind) {
        for (KeyBinding key : specials) {
            if (key.getKeyCode() == keybind) {
                return key;
            }
        }
        return null;
    }
}
