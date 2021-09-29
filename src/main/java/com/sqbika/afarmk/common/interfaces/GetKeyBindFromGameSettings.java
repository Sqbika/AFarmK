package com.sqbika.afarmk.common.interfaces;


import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;

@FunctionalInterface
public interface GetKeyBindFromGameSettings {

    KeyBinding doCallback(GameOptions settings);
}
