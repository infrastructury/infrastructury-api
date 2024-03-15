package com.mrmelon54.OmniPlay.forge;

import com.mrmelon54.OmniPlay.OmniPlay;
import com.mrmelon54.omniver.forge.ConfigGui;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(OmniPlay.MOD_ID)
public class OmniPlayForge {
    public OmniPlayForge() {
        EventBuses.registerModEventBus(OmniPlay.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ConfigGui.register((mc, screen) -> OmniPlay.createConfigScreen(screen).get());
        OmniPlay.init();
    }
}
