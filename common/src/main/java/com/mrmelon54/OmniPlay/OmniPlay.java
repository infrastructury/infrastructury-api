package com.mrmelon54.OmniPlay;

import com.mrmelon54.OmniPlay.util.ConfigScreenRegistrar;
import dev.architectury.injectables.annotations.ExpectPlatform;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.util.function.Supplier;

public class OmniPlay {
    public static final String MOD_ID = "omniplay";
    public static ConfigStructure CONFIG = AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new).get();

    public static void init() {
        registerConfigScreen((mc, screen) -> createConfigScreen(screen).get());
    }

    public static Supplier<Screen> createConfigScreen(Screen screen) {
        return AutoConfig.getConfigScreen(ConfigStructure.class, screen);
    }

    public static boolean showDebugScreen() {
        #if MC_VER < MC_1_20_2
        return Minecraft.getInstance().options.renderDebug;
        #else
        return Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen();
        #endif
    }

    @ExpectPlatform
    public static void registerConfigScreen(ConfigScreenRegistrar registrar) {
        throw new AssertionError();
    }
}
