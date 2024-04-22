package com.mrmelon54.infrastructury.registry.registries;

import com.mrmelon54.infrastructury.utils.OptionalSupplier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public interface DeferredSupplier<T> extends OptionalSupplier<T> {
    static <T> DeferredSupplier<T> of(remapped.architectury.registry.registries.DeferredSupplier<T> supplier) {
        return new DeferredSupplier<T>() {
            @Override
            public T get() {
                return supplier.get();
            }

            @Override
            public boolean isPresent() {
                return supplier.isPresent();
            }

            @Override
            public ResourceLocation getRegistryId() {
                return supplier.getRegistryId();
            }

            @Override
            public ResourceLocation getId() {
                return supplier.getId();
            }
        };
    }

    default remapped.architectury.registry.registries.DeferredSupplier<T> back() {
        return new remapped.architectury.registry.registries.DeferredSupplier<>() {
            @Override
            public ResourceLocation getRegistryId() {
                return DeferredSupplier.this.getRegistryId();
            }

            @Override
            public ResourceLocation getId() {
                return DeferredSupplier.this.getId();
            }

            @Override
            public boolean isPresent() {
                return DeferredSupplier.this.isPresent();
            }

            @Override
            public T get() {
                return DeferredSupplier.this.get();
            }
        };
    }

    ResourceLocation getRegistryId();

    default ResourceKey<Registry<T>> getRegistryKey() {
        return ResourceKey.createRegistryKey(getRegistryId());
    }

    ResourceLocation getId();

    default ResourceKey<T> getKey() {
        return ResourceKey.create(getRegistryKey(), getId());
    }
}
