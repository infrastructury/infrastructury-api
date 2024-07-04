package com.mrmelon54.infrastructury;

import com.mrmelon54.infrastructury.utils.ConfigScreenRegistrar;
import dev.architectury.injectables.annotations.ExpectPlatform;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

import java.util.function.Supplier;

public class Infrastructury {
    public static final String MOD_ID = "infrastructury";

    public static void init() {
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
        // Implemented by each platform
    }
}
