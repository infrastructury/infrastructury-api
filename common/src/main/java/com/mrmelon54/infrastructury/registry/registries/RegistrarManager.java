package com.mrmelon54.infrastructury.registry.registries;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class RegistrarManager {
    private final remapped.architectury.registry.registries.RegistrarManager registrarManager;

    private RegistrarManager(remapped.architectury.registry.registries.RegistrarManager registrarManager) {
        this.registrarManager = registrarManager;
    }

    public static RegistrarManager get(String modId) {
        return new RegistrarManager(remapped.architectury.registry.registries.RegistrarManager.get(modId));
    }

    public <T> Registrar<T> get(ResourceKey<Registry<T>> key) {
        return new RegistrarImpl<>(registrarManager.get(key));
    }
}
