package com.mrmelon54.infrastructury.registry.registries;

import net.minecraft.core.Holder;
import remapped.architectury.registry.registries.Registrar;
import remapped.architectury.registry.registries.RegistrarManager;

import java.util.function.Consumer;

public interface RegistrySupplier<T> extends DeferredSupplier<T>, Holder<T> {
    remapped.architectury.registry.registries.RegistrySupplier<T> architectury$convert();

    RegistrarManager getRegistrarManager();

    Registrar<T> getRegistrar();

    default void listen(Consumer<T> callback) {
        this.getRegistrar().listen(architectury$convert(), callback);
    }
}
