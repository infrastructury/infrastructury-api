package com.mrmelon54.infrastructury.registry.registries;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.impl.RegistrySupplierImpl;
import remapped.architectury.registry.registries.Registrar;
import remapped.architectury.registry.registries.RegistrarManager;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface RegistrySupplier<T> extends DeferredSupplier<T>, Holder<T> {
    static <T> RegistrySupplier<T> of(remapped.architectury.registry.registries.RegistrySupplier<T> supplier) {
        return new RegistrySupplier<T>() {
            @Override
            public remapped.architectury.registry.registries.RegistrySupplier<T> architectury$convert() {
                return supplier;
            }

            @Override
            public RegistrarManager getRegistrarManager() {
                return supplier.getRegistrarManager();
            }

            @Override
            public Registrar<T> getRegistrar() {
                return supplier.getRegistrar();
            }

            @Override
            public ResourceLocation getRegistryId() {
                return supplier.getRegistryId();
            }

            @Override
            public ResourceLocation getId() {
                return supplier.getId();
            }

            @Override
            public boolean isPresent() {
                return supplier.isPresent();
            }

            @Override
            public T get() {
                return supplier.get();
            }

            @Override
            public T value() {
                return supplier.value();
            }

            @Override
            public boolean isBound() {
                return supplier.isBound();
            }

            @Override
            public boolean is(ResourceLocation location) {
                return supplier.is(location);
            }

            @Override
            public boolean is(ResourceKey<T> resourceKey) {
                return supplier.is(resourceKey);
            }

            @Override
            public boolean is(Predicate<ResourceKey<T>> predicate) {
                return supplier.is(predicate);
            }

            @Override
            public boolean is(TagKey<T> tagKey) {
                return supplier.is(tagKey);
            }

            @Override
            public Stream<TagKey<T>> tags() {
                return supplier.tags();
            }

            @Override
            public @NotNull Either<ResourceKey<T>, T> unwrap() {
                return supplier.unwrap();
            }

            @Override
            public @NotNull Optional<ResourceKey<T>> unwrapKey() {
                return supplier.unwrapKey();
            }

            @Override
            public @NotNull Kind kind() {
                return supplier.kind();
            }

            @Override
            public boolean canSerializeIn(HolderOwner<T> owner) {
                return supplier.canSerializeIn(owner);
            }
        };
    }

    remapped.architectury.registry.registries.RegistrySupplier<T> architectury$convert();

    RegistrarManager getRegistrarManager();

    Registrar<T> getRegistrar();

    default void listen(Consumer<T> callback) {
        this.getRegistrar().listen(architectury$convert(), callback);
    }
}
