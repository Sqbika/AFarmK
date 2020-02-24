package com.sqbika.afarmk.common.interfaces;


import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;

@FunctionalInterface
public interface GetKeyBindFromGameSettings {

    KeyBinding doCallback(GameOptions settings);
}
