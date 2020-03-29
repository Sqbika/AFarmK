package com.sqbika.afarmk;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.config.AFarmKConfig;
import com.sqbika.afarmk.common.config.ConfigHandler;
import com.sqbika.afarmk.common.config.TogglerProfile;
import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod(Constants.MOD_ID)
public final class AFarmK {

    public static AFarmKConfig config;

    public AFarmK() {
        AFarmKConfig config = ConfigHandler.readConfig();
        AFarmK.config = config;

        for (BUTTON_TOGGLES buttonToggle : BUTTON_TOGGLES.values()) {
            ClientRegistry.registerKeyBinding(buttonToggle.getKeybinding());
        }
        if (Objects.nonNull(config) && Objects.nonNull(config.profiles)) {
            for (TogglerProfile profile : config.profiles) {
                if (profile.buttons.length != 0 && profile.profileKeybind != 0) {
                    KeyBinding keybind = new KeyBinding(profile.name, profile.profileKeybind, Constants.PROFILE_CATEGORY);
                    profile.setKeyBinding(keybind);
                    ClientRegistry.registerKeyBinding(keybind);
                }
            }
        }
    }
}
