package com.mrmelon54.OmniPlay.forge;

import com.mrmelon54.OmniPlay.util.ConfigScreenRegistrar;
import net.minecraftforge.fml.ModLoadingContext;

#if MC_VER == MC_1_16_5
import net.minecraftforge.fml.ExtensionPoint;
#elif MC_VER <= MC_1_17_1
import net.minecraftforge.fmlclient.ConfigGuiHandler;
#elif MC_VER <= MC_1_18_2
import net.minecraftforge.client.ConfigGuiHandler;
#else
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
#endif

public class OmniPlayImpl {
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
