package com.mrmelon54.OmniPlay.forge;

import com.mrmelon54.OmniPlay.OmniPlay;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.platform.forge.EventBuses;
#else
import dev.architectury.platform.forge.EventBuses;
#endif

@Mod(OmniPlay.MOD_ID)
public class OmniPlayForge {
    public OmniPlayForge() {
        EventBuses.registerModEventBus(OmniPlay.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        OmniPlay.init();
    }
}
