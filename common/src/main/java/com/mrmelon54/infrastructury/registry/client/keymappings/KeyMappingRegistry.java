package com.mrmelon54.infrastructury.registry.client.keymappings;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;

@Environment(EnvType.CLIENT)
public final class KeyMappingRegistry {
    private KeyMappingRegistry() {
    }

    public static void register(KeyMapping keyMapping) {
        remapped.architectury.registry.client.keymappings.KeyMappingRegistry.register(keyMapping);
    }
}
