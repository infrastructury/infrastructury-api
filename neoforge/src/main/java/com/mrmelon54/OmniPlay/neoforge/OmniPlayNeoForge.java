package com.mrmelon54.OmniPlay.neoforge;

import com.mrmelon54.OmniPlay.OmniPlay;
import com.mrmelon54.OmniPlay.util.ConfigScreenRegistrar;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

@Mod(OmniPlay.MOD_ID)
public class OmniPlayNeoForge {
    public OmniPlayNeoForge() {
        registerConfigScreen((mc,screen)->OmniPlay.createConfigScreen(screen).get());
        OmniPlay.init();
    }

    public static void registerConfigScreen(ConfigScreenRegistrar registrar) {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class,()->new ConfigScreenFactory(registrar::registerConfigScreen));
    }
}
