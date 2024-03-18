package com.mrmelon54.OmniPlay.forge;

import com.mrmelon54.OmniPlay.OmniPlay;
import com.mrmelon54.OmniPlay.util.ConfigScreenRegistrar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.ExtensionPoint;
#elif MC_VER <= MC_1_17_1
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fmlclient.ConfigGuiHandler;
#elif MC_VER <= MC_1_18_2
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.client.ConfigGuiHandler;
#else
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import dev.architectury.platform.forge.EventBuses;
#endif

@Mod(OmniPlay.MOD_ID)
public class OmniPlayForge {
    public OmniPlayForge() {
        EventBuses.registerModEventBus(OmniPlay.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        registerConfigScreen((mc, screen) -> OmniPlay.createConfigScreen(screen).get());
        OmniPlay.init();
    }

    public static void registerConfigScreen(ConfigScreenRegistrar registrar) {
         #if MC_VER == MC_1_16_5
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> registrar::registerConfigScreen);
        #elif MC_VER <= MC_1_18_2
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory(registrar::registerConfigScreen));
        #else
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory(registrar::registerConfigScreen));
        #endif
    }
}
