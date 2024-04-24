package com.mrmelon54.infrastructury.platform;

import com.mrmelon54.infrastructury.utils.Env;
import net.fabricmc.api.EnvType;
import net.minecraft.SharedConstants;

import java.nio.file.Path;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Platform {
    private Platform() {
    }

    public static boolean isFabric() {
        return remapped.architectury.platform.Platform.isFabric();
    }

    public static boolean isForgeLike() {
        return isMinecraftForge() || isNeoForge();
    }

    public static boolean isMinecraftForge() {
        #if MC_VER > MC_1_20_1
        return remapped.architectury.platform.Platform.isMinecraftForge();
        #else
        return remapped.architectury.platform.Platform.isForge();
        #endif
    }

    public static boolean isNeoForge() {
        #if MC_VER > MC_1_20_1
        return remapped.architectury.platform.Platform.isNeoForge();
        #else
        return false;
        #endif
    }

    public static String getMinecraftVersion() {
        return SharedConstants.getCurrentVersion().getId();
    }

    public static Path getGameFolder() {
        return remapped.architectury.platform.Platform.getGameFolder();
    }

    public static Path getConfigFolder() {
        return remapped.architectury.platform.Platform.getConfigFolder();
    }

    public static Path getModsFolder() {
        return remapped.architectury.platform.Platform.getModsFolder();
    }

    public static Env getEnvironment() {
        return Env.convert(remapped.architectury.platform.Platform.getEnvironment());
    }

    public static EnvType getEnv() {
        return remapped.architectury.platform.Platform.getEnv();
    }

    public static boolean isModLoaded(String id) {
        return remapped.architectury.platform.Platform.isModLoaded(id);
    }

    public static Mod getMod(String id) {
        return Mod.convert(remapped.architectury.platform.Platform.getMod(id));
    }

    public static Optional<Mod> getOptionalMod(String id) {
        try {
            return Optional.of(getMod(id));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public static Collection<Mod> getMods() {
        return remapped.architectury.platform.Platform.getMods().stream().map(Mod::convert).collect(Collectors.toList());
    }

    public static Collection<String> getModIds() {
        return remapped.architectury.platform.Platform.getModIds();
    }

    public static boolean isDevelopmentEnvironment() {
        return remapped.architectury.platform.Platform.isDevelopmentEnvironment();
    }
}
