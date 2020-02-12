package com.sqbika.afarmk.gui;

import com.sqbika.afarmk.toggle.AFarmKConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ToggleGUI extends Gui {

    @SubscribeEvent
    public void renderGui(RenderGameOverlayEvent e) {
        if (AFarmKConfig.guiSettings.enableGui && e.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            Minecraft mc = Minecraft.getMinecraft();
            ScaledResolution scaled = new ScaledResolution(mc);
            FontRenderer frender = mc.fontRenderer;

            int width = scaled.getScaledWidth();
            int height = scaled.getScaledHeight();

            String onString = AFarmKConfig.getOnString();

            int x = AFarmKConfig.guiSettings.posX == -1 ? frender.getStringWidth(onString) + 10 : AFarmKConfig.guiSettings.posX;
            int y = AFarmKConfig.guiSettings.posY == -1 ? 20 : AFarmKConfig.guiSettings.posY;


            if (!onString.equals("")) {
                onString = "P:" + onString;
                drawString(frender, onString, width - x, height - (y), 3045098);
            }
        }
    }

}
