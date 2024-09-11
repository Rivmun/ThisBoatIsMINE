package com.rimo.tbim;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@SuppressWarnings("unused")
@Mod(ModMain.MOD_ID)
public class NeoForgeMod {
    public NeoForgeMod(IEventBus eventBus) {
        ModMain.init();
    }
}
