package com.sqbika.afarmk.toggle;

import com.sqbika.afarmk.common.Constants;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.BitSet;

@Config(modid = Constants.MOD_ID)
public class AFarmKConfig {

    public static TogglerProfile profile1 = new TogglerProfile();
    public static TogglerProfile profile2 = new TogglerProfile();
    public static TogglerProfile profile3 = new TogglerProfile();
    public static TogglerProfile profile4 = new TogglerProfile();
    public static TogglerProfile profile5 = new TogglerProfile();
    public static TogglerProfile profile6 = new TogglerProfile();
    public static TogglerProfile profile7 = new TogglerProfile();
    public static TogglerProfile profile8 = new TogglerProfile();
    public static TogglerProfile profile9 = new TogglerProfile();

    public static TogglerProfile get(int id) {
        switch (id) {
            case 1: return profile1;
            case 2: return profile2;
            case 3: return profile3;
            case 4: return profile4;
            case 5: return profile5;
            case 6: return profile6;
            case 7: return profile7;
            case 8: return profile8;
            case 9: return profile9;
            default: return null;
        }
    }

    @Config.Comment({"You can enable/disable profiles.", "It'll free up 9 keybinds (GUI Work in Progress) (Default Keybinds: (ALT + NumPad_1 to NumPad_9))"})
    public static boolean enableProfiles = true;

    @Config.Comment({"You can enable/disable individual toggles.", "It'll free up 9 keybinds (GUI Work in Progress) (Default Keybinds: (NumPad_1 to NumPad_9)) "})
    public static boolean enableIndividual = true;

    public static GuiSettings guiSettings = new GuiSettings();

    public static String getOnString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            if (get(i).on) {
                builder.append(" ");
                builder.append(i);
            }
        }
        return builder.toString();
    }

    public static class TogglerProfile {
        public boolean toggleShift = false;
        public boolean toggleLeftClick = false;
        public boolean toggleRightClick = false;
        public boolean toggleForward = false;
        public boolean toggleBackwards = false;
        public boolean toggleLeft = false;
        public boolean toggleRight = false;
        public boolean toggleJump = false;
        public boolean enabled = true;

        //Is the profile running?
        @Config.Ignore
        public boolean on = false;

        public BitSet getProfileState() {
            BitSet set = new BitSet(8);
            set.set(0, toggleShift);
            set.set(1, toggleLeftClick);
            set.set(2, toggleRightClick);
            set.set(3, toggleForward);
            set.set(4, toggleBackwards);
            set.set(5, toggleLeft);
            set.set(6, toggleRight);
            set.set(7, toggleJump);
            return set;
        }

        public void reset() {
            on = false;
        }
    }

    public static class GuiSettings {
        @Config.Comment({"You can enable/disable the GUI, which shows the current active profiles and toggles.", "Example:", "P: 1", "I: 1 3", "", "This means that Profile 1 is active and 1 3 toggle is on."})
        public boolean enableGui = true;

        @Config.Comment({"Vertical position of the GUI.", "If it's -1, then it'll put it to the right."})
        public int posX = -1;

        @Config.Comment({"Horizontal position of the GUI.", "If it's -1, then it'll put it to the bottom."})
        public int posY = -1;
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID)
    private static class Handler {

        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Constants.MOD_ID)) {
                ConfigManager.sync(Constants.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
