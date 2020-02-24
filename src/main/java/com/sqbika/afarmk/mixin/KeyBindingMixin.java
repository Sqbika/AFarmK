package com.sqbika.afarmk.mixin;

import com.sqbika.afarmk.common.enums.BUTTON_TOGGLES;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {

    @Inject(at = @At("RETURN"), method = "onKeyPressed")
    private static void onKeyPressed(InputUtil.KeyCode keyCode, CallbackInfo info) {
        for (BUTTON_TOGGLES toggle : BUTTON_TOGGLES.values()) {
            if (toggle.getKeybinding().matchesKey(keyCode.getKeyCode(), 0)) {
                KeyBinding bind = toggle.getToggleKeybind(MinecraftClient.getInstance().options);
                bind.setPressed(!bind.isPressed());
            }
        }
    }
}
