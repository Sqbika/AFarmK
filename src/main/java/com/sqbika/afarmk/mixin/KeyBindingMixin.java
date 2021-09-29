package com.sqbika.afarmk.mixin;

import com.sqbika.afarmk.AFarmK;
import com.sqbika.afarmk.LogHelper;
import com.sqbika.afarmk.common.config.TogglerProfile;
import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(at = @At("RETURN"), method = "onKeyPressed")
    private static void onKeyPressed(InputUtil.Key keyCode, CallbackInfo info) {
        for (BUTTON_TOGGLES toggle : BUTTON_TOGGLES.values()) {
            if (toggle.getKeybinding().matchesKey(keyCode.getCode(), 0)) {
                KeyBinding bind = toggle.getToggleKeybind(MinecraftClient.getInstance().options);
                bind.setPressed(!bind.isPressed());
            }
        }
        for (TogglerProfile profile : AFarmK.config.profiles) {
            if (profile.buttons.length != 0 && profile.keyBinding.matchesKey(keyCode.getCode(), 0)) {
                for (String code : profile.buttons) {
                    try {
                        BUTTON_TOGGLES toggle = BUTTON_TOGGLES.valueOf(code);
                        KeyBinding bind = toggle.getToggleKeybind(MinecraftClient.getInstance().options);
                        bind.setPressed(!bind.isPressed());
                    } catch (RuntimeException e) {
                        LogHelper.fatal(String.format("The enum [%s] was not found in the BUTTON_TOGGLES!", code), e);
                    }
                }
            }
        }
    }
}
