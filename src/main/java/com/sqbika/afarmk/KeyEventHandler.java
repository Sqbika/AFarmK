package com.sqbika.afarmk;

public class KeyEventHandler {

   /* @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent event) {
        if (!Minecraft.getMinecraft().inGameHasFocus) return;
        GameSettings settings = FMLClientHandler.instance().getClient().gameSettings;
        if (!Keyboard.isKeyDown(Keyboard.KEY_LMENU)) {
            BUTTON_TOGGLES bt = BUTTON_TOGGLES.findByKeyBind(Keyboard.getEventKey());
            if (!isNull(bt)) {
                KeyBinding key = bt.getKeybinding();
                if (!isNull(key) && key.isKeyDown()) {
                    BUTTON_TOGGLES bind = BUTTON_TOGGLES.get(key.getKeyDescription());
                    if (!isNull(bind)) {
                        KeyBinding kbbind = bind.getToggleKeybind(settings);
                        KeyBinding.setKeyBindState(kbbind.getKeyCode(), !kbbind.isKeyDown());
                    } else {
                        specialCases(key.getKeyDescription(), settings);
                    }
                }
            }
        } else {
            KeyBinding key = Keybindings.findSpecial(Keyboard.getEventKey());
            if (!isNull(key) && key.isKeyDown())
                specialCases(key.getKeyDescription(), settings);
        }
    }

    private void specialCases(String desc, GameSettings sett) {
        if (desc.startsWith("toggle.profile")) {
            profileCases(desc, sett);
            return;
        }
        if ("toggle.reset".equals(desc)) {
            for (BUTTON_TOGGLES key : BUTTON_TOGGLES.values()) {
                KeyBinding.setKeyBindState(key.getToggleKeybind(sett).getKeyCode(), false);
            }
        }
    }

    private void profileCases(String desc, GameSettings sett) {
        int profile_id = Integer.parseInt(desc.replace("toggle.profile", ""));
        AFarmKConfig.TogglerProfile profile = AFarmKConfig.get(profile_id);
        if (!isNull(profile)) {
            BitSet set = profile.getProfileState();
            for (int i = 0; i < set.length(); i++) {
                if (set.get(i)) {
                    BUTTON_TOGGLES button_toggles = BUTTON_TOGGLES.get(i);
                    KeyBinding.setKeyBindState(button_toggles.getToggleKeybind(sett).getKeyCode(), !profile.on);
                }
            }
            profile.on = !profile.on;
        }
    }*/
}
