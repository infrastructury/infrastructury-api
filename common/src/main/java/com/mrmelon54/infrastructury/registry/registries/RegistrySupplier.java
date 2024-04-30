package com.mrmelon54.infrastructury.registry.registries;

import com.mojang.datafixers.util.Either;
import com.mrmelon54.infrastructury.utils.OptionalSupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;
import remapped.architectury.registry.registries.Registrar;
import remapped.architectury.registry.registries.RegistrarManager;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface RegistrySupplier<T> extends OptionalSupplier<T>, Holder<T> {
    static <T> RegistrySupplier<T> of(remapped.architectury.registry.registries.RegistrySupplier<T> supplier) {
        return new RegistrySupplier<>() {
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
            public @NotNull T value() {
                #if MC_VER > MC_1_20_2
                return supplier.value();
                #else
                return supplier.get();
                #endif
            }

            @Override
            public boolean isBound() {
                #if MC_VER > MC_1_20_2
                return supplier.isBound();
                #else
                return false;
                #endif
            }

            @Override
            public boolean is(ResourceLocation location) {
                #if MC_VER > MC_1_20_2
                return supplier.is(location);
                #else
                return location.equals(getId());
                #endif
            }

            @Override
            public boolean is(ResourceKey<T> resourceKey) {
                #if MC_VER > MC_1_20_2
                return supplier.is(resourceKey);
                #else
                return supplier.isPresent();
                #endif
            }

            @Override
            public boolean is(Predicate<ResourceKey<T>> predicate) {
                #if MC_VER > MC_1_20_2
                return supplier.is(predicate);
                #else
                return predicate.test(getKey());
                #endif
            }

            @Override
            public boolean is(TagKey<T> tagKey) {
                #if MC_VER > MC_1_20_2
                return supplier.is(tagKey);
                #else
                return tagKey.location().equals(getId());
                #endif
            }

            @Override
            public Stream<TagKey<T>> tags() {
                #if MC_VER > MC_1_20_2
                return supplier.tags();
                #else
                return Stream.empty();
                #endif
            }

            @Override
            public @NotNull Either<ResourceKey<T>, T> unwrap() {
                #if MC_VER > MC_1_20_2
                return supplier.unwrap();
                #else
                return !supplier.isPresent() ? Either.left(supplier.getKey()) : Either.right(supplier.get());
                #endif
            }

            @Override
            public @NotNull Optional<ResourceKey<T>> unwrapKey() {
                #if MC_VER > MC_1_20_2
                return supplier.unwrapKey();
                #else
                return Optional.of(supplier.getKey());
                #endif
            }

            @Override
            public @NotNull Kind kind() {
                #if MC_VER > MC_1_20_2
                return supplier.kind();
                #else
                return !supplier.isPresent() ? Kind.REFERENCE : Kind.DIRECT;
                #endif
            }

            @Override
            public boolean canSerializeIn(HolderOwner<T> owner) {
                #if MC_VER > MC_1_20_2
                return supplier.canSerializeIn(owner);
                #else
                return false;
                #endif
            }
        };
    }

    remapped.architectury.registry.registries.RegistrySupplier<T> architectury$convert();

    RegistrarManager getRegistrarManager();

    Registrar<T> getRegistrar();

    ResourceLocation getRegistryId();

    default ResourceKey<Registry<T>> getRegistryKey() {
        return ResourceKey.createRegistryKey(this.getRegistryId());
    }

    ResourceLocation getId();

    default ResourceKey<T> getKey() {
        return ResourceKey.create(this.getRegistryKey(), this.getId());
    }

    default void listen(Consumer<T> callback) {
        this.getRegistrar().listen(architectury$convert(), callback);
    }
}
