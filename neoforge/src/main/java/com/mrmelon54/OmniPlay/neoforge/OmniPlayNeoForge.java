package com.mrmelon54.OmniPlay.neoforge;

import com.mrmelon54.OmniPlay.OmniPlay;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

@Mod(OmniPlay.MOD_ID)
public class OmniPlayNeoForge {
    public OmniPlayNeoForge() {
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> OmniPlay.createConfigScreen(screen).get()));
        OmniPlay.init();
    }
}
