package com.mrmelon54.infrastructury.platform;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Mod {
    String getModId();

    String getVersion();

    String getName();

    String getDescription();

    Optional<String> getLogoFile(int preferredSize);

    List<Path> getFilePaths();

    Optional<Path> findResource(String... path);

    Collection<String> getAuthors();

    @Nullable
    Collection<String> getLicense();

    Optional<String> getHomepage();

    Optional<String> getSources();

    Optional<String> getIssueTracker();

    @Environment(EnvType.CLIENT)
    void registerConfigurationScreen(ConfigurationScreenProvider provider);

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    interface ConfigurationScreenProvider {
        Screen provide(Screen parent);
    }

    static Mod convert(remapped.architectury.platform.Mod mod) {
        return new Mod() {
            @Override
            public String getModId() {
                return mod.getModId();
            }

            @Override
            public String getVersion() {
                return mod.getVersion();
            }

            @Override
            public String getName() {
                return mod.getName();
            }

            @Override
            public String getDescription() {
                return mod.getDescription();
            }

            @Override
            public Optional<String> getLogoFile(int preferredSize) {
                return mod.getLogoFile(preferredSize);
            }

            @Override
            public List<Path> getFilePaths() {
                return mod.getFilePaths();
            }

            @Override
            public Optional<Path> findResource(String... path) {
                #if MC_VER <= MC_1_18_2
                return Optional.empty();
                #else
                return mod.findResource(path);
                #endif
            }

            @Override
            public Collection<String> getAuthors() {
                return mod.getAuthors();
            }

            @Override
            public @Nullable Collection<String> getLicense() {
                return mod.getLicense();
            }

            @Override
            public Optional<String> getHomepage() {
                return mod.getHomepage();
            }

            @Override
            public Optional<String> getSources() {
                return mod.getSources();
            }

            @Override
            public Optional<String> getIssueTracker() {
                return mod.getIssueTracker();
            }

            @Override
            public void registerConfigurationScreen(ConfigurationScreenProvider provider) {
                mod.registerConfigurationScreen(provider::provide);
            }
        };
    }
}
