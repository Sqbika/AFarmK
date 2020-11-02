package com.sqbika.afarmk.mixin;

import com.sqbika.afarmk.common.Constants;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(I18n.class)
public class I18nMixin {

    @Inject(at = @At("RETURN"), method="translate", cancellable = true)
    private static void translate(String key, Object[] args, CallbackInfoReturnable<String> info) {
        if (Objects.nonNull(key) && key.startsWith(Constants.PROFILE_TR_PREFIX) && key.equals(info.getReturnValue())) {
            info.setReturnValue(key.replace(Constants.PROFILE_TR_PREFIX, "").replaceAll("_", " "));
        }
    }
}
