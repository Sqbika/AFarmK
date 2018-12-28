package com.sqbika.afarmk;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    private static ClientProxy ourInstance = new ClientProxy();

    public static ClientProxy getInstance() {
        return ourInstance;
    }

    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new KeyEventHandler());
        Keybindings.init();
    }
}
