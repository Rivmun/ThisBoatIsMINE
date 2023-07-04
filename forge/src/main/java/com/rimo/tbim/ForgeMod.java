package com.rimo.tbim;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("tbim")
public class ForgeMod {
    public ForgeMod() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus("tbim", FMLJavaModLoadingContext.get().getModEventBus());
        ModMain.init();
    }
}
