package com.sqbika.afarmk;

import com.sqbika.afarmk.GUI.ToggleGUI;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;

public class ClientProxy extends CommonProxy {
    private static ClientProxy ourInstance = new ClientProxy();

    public static ClientProxy getInstance() {
        return ourInstance;
    }

    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new KeyEventHandler());
        MinecraftForge.EVENT_BUS.register(new ToggleGUI());
        Values.toggles = new ArrayList<>();
        Keybindings.init();
    }
}
