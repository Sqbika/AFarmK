package com.sqbika.afarmk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class KeyEventHandler {

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        KeyBinding key = Keybindings.find(Keyboard.getEventKey());
        if (!isNull(key) && key.isKeyDown()) {
            GameSettings settings = FMLClientHandler.instance().getClient().gameSettings;
            KeyBinding bind = getKeybindFromDesc(key.getKeyDescription(), settings);
            if (!isNull(bind))
                KeyBinding.setKeyBindState(bind.getKeyCode(), !bind.isKeyDown());
        }
    }

    private KeyBinding getKeybindFromDesc(String desc, GameSettings sett) {
        switch (desc) {
            case "toggle.shift": return sett.keyBindSneak;
            case "toggle.leftClick": return sett.keyBindAttack;
            case "toggle.rightClick": return sett.keyBindUseItem;
            case "toggle.forward": return sett.keyBindForward;
            case "toggle.backwards": return sett.keyBindBack;
            case "toggle.left": return sett.keyBindLeft;
            case "toggle.right": return sett.keyBindRight;
            case "toggle.jump": return sett.keyBindJump;
            default: return null;
        }
    }
}
