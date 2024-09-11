package com.rimo.tbim;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModMain.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModMain.init();
        eventBus.register(this);
    }
}
