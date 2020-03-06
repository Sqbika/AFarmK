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
        KeyBindingRegistry.INSTANCE.addCategory(Constants.TOGGLER_CATEGORY);
        for (BUTTON_TOGGLES buttonToggle : BUTTON_TOGGLES.values()) {
            KeyBindingRegistry.INSTANCE.register(buttonToggle.getKeybinding());
        }
        if (Objects.nonNull(config) && Objects.nonNull(config.profiles)) {
            KeyBindingRegistry.INSTANCE.addCategory(Constants.PROFILE_CATEGORY);
            for (TogglerProfile profile : config.profiles) {
                if (profile.buttons.length != 0 && profile.profileKeybind != 0) {
                    FabricKeyBinding keybind = FabricKeyBinding.Builder.create(new Identifier(Constants.MOD_ID, "profile." + profile.name.toLowerCase().replaceAll(" ", "_")), InputUtil.Type.KEYSYM, profile.profileKeybind, Constants.PROFILE_CATEGORY).build();
                    KeyBindingRegistry.INSTANCE.register(keybind);
                    profile.setKeyBinding(keybind);
                }
            }
        }
    }
}
