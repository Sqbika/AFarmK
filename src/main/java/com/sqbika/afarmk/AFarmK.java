package com.sqbika.afarmk;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Values.ModID, name = Values.ModName, version = Values.ModVersion)
public class AFarmK {

    @Mod.Instance
    public static AFarmK instance;

    @SidedProxy(clientSide = Values.ClientProxy, serverSide = Values.CommonProxy)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }
}