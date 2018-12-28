package com.sqbika.afarmk;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    private static CommonProxy ourInstance = new CommonProxy();

    public static CommonProxy getInstance() {
        return ourInstance;
    }

    public void preInit(FMLPreInitializationEvent event){
        if (event.getSide().isServer())
            LogHelper.warn("This mod is not for server use. It will do no harm, but it'll just sit there and exist.");
    }
}
