package com.mrmelon54.OmniPlay;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.util.function.Supplier;

public class OmniPlay {
    public static final String MOD_ID = "omniplay";
    public static ConfigStructure CONFIG = AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new).get();

    public static void init() {
        // TODO
    }

    public static Supplier<Screen> createConfigScreen(Screen screen) {
        return AutoConfig.getConfigScreen(ConfigStructure.class, screen);
    }

    public static boolean showDebugScreen() {
        #if MC_VER > MC_1_20_1
        return Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen();
        #else
        return Minecraft.getInstance().options.renderDebug;
        #endif
    }
}
