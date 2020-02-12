package com.sqbika.afarmk.common.interfaces;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

@FunctionalInterface
public interface GetKeyBindFromGameSettings {

    KeyBinding doCallback(GameSettings settings);
}
