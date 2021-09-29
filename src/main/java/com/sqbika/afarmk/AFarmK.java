package com.sqbika.afarmk;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.config.AFarmKConfig;
import com.sqbika.afarmk.common.config.ConfigHandler;
import com.sqbika.afarmk.common.config.TogglerProfile;
import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

public class AFarmK implements ClientModInitializer {

    public static AFarmKConfig config;

    @Override
    public void onInitializeClient() {
        AFarmKConfig config = ConfigHandler.readConfig();
        AFarmK.config = config;

        BUTTON_TOGGLES.registerKeybinds();

        if (Objects.nonNull(config) && Objects.nonNull(config.profiles)) {
            for (TogglerProfile profile : config.profiles) {
                if (profile.buttons.length != 0 && profile.profileKeybind != 0) {
                    KeyBinding kb = new KeyBinding(Constants.KEY_PREFIX + ".profile." + profile.name.toLowerCase().replaceAll(" ", "_"), InputUtil.Type.KEYSYM, profile.profileKeybind, Constants.PROFILE_CATEGORY);
                    KeyBindingHelper.registerKeyBinding(kb);
                    profile.setKeyBinding(kb);
                }
            }
        }
    }
}
