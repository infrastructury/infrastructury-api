package com.mrmelon54.OmniPlay.neoforge;

import com.mrmelon54.OmniPlay.util.ConfigScreenRegistrar;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

public class OmniPlayImpl {
    public static void registerConfigScreen(ConfigScreenRegistrar registrar) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class,()->new ConfigScreenFactory(registrar::registerConfigScreen));
    }
}
