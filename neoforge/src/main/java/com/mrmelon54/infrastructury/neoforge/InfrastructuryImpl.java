package com.mrmelon54.infrastructury.neoforge;

import com.mrmelon54.infrastructury.utils.ConfigScreenRegistrar;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

public class InfrastructuryImpl {
    public static void registerConfigScreen(ConfigScreenRegistrar registrar) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class,()->new ConfigScreenFactory(registrar::registerConfigScreen));
    }
}
