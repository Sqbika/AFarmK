package com.sqbika.afarmk;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.config.AFarmKConfig;
import com.sqbika.afarmk.common.config.ConfigHandler;
import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.glfw.GLFW;

public class AFarmK implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        AFarmKConfig config = ConfigHandler.readConfig();
        KeyBindingRegistry.INSTANCE.addCategory(Constants.TOGGLER_CATEGORY);
        for (BUTTON_TOGGLES buttonToggle : BUTTON_TOGGLES.values()) {
            KeyBindingRegistry.INSTANCE.register(buttonToggle.getKeybinding());
        }
    }
}
