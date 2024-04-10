package com.mrmelon54.infrastructury.forge;

import com.mrmelon54.infrastructury.Infrastructury;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.platform.forge.EventBuses;
#else
import dev.architectury.platform.forge.EventBuses;
#endif

@Mod(Infrastructury.MOD_ID)
public class InfrastructuryForge {
    public InfrastructuryForge() {
        EventBuses.registerModEventBus(Infrastructury.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Infrastructury.init();
    }
}
