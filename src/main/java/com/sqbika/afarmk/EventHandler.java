package com.sqbika.afarmk;

import com.sqbika.afarmk.common.Constants;
import com.sqbika.afarmk.common.config.TogglerProfile;
import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandler {

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.KeyInputEvent event) {
        if (event.getAction() != GLFW.GLFW_PRESS) return;
        for (BUTTON_TOGGLES toggle : BUTTON_TOGGLES.values()) {
            if (toggle.getKeybinding().matchesKey(event.getKey(), 0)) {
                KeyBinding bind = toggle.getToggleKeybind(Minecraft.getInstance().gameSettings);
                KeyBinding.setKeyBindState(bind.getKey(), !bind.isKeyDown());
                return;
            }
        }
        for (TogglerProfile profile : AFarmK.config.profiles) {
            if (profile.buttons.length != 0 && profile.keyBinding.matchesKey(event.getKey(), 0)) {
                for (String code : profile.buttons) {
                    try {
                        BUTTON_TOGGLES toggle = BUTTON_TOGGLES.valueOf(code);
                        KeyBinding bind = toggle.getToggleKeybind(Minecraft.getInstance().gameSettings);
                        KeyBinding.setKeyBindState(bind.getKey(), !bind.isKeyDown());
                    } catch (RuntimeException e) {
                        LogHelper.fatal(String.format("The enum [%s] was not found in the BUTTON_TOGGLES!", code), e);
                    }
                }
            }
        }
    }
}
