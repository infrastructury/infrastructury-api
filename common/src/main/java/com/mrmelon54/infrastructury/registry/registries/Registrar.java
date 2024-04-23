package com.mrmelon54.infrastructury.registry.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Registrar<T> extends Iterable<T> {
    RegistrySupplier<T> delegate(ResourceLocation id);

    default <R extends T> RegistrySupplier<R> wrap(R obj) {
        ResourceLocation id = getId(obj);

        if (id == null) {
            throw new IllegalArgumentException("Cannot wrap an object without an id: " + obj);
        } else {
            return (RegistrySupplier<R>) delegate(id);
        }
    }

    <E extends T> RegistrySupplier<E> register(ResourceLocation id, Supplier<E> supplier);

    @Nullable
    ResourceLocation getId(T obj);

    int getRawId(T obj);

    Optional<ResourceKey<T>> getKey(T obj);

    @Nullable
    T get(ResourceLocation id);

    @Nullable
    T byRawId(int rawId);

    boolean contains(ResourceLocation id);

    boolean containsValue(T obj);

    Set<ResourceLocation> getIds();

    Set<Map.Entry<ResourceKey<T>, T>> entrySet();

    ResourceKey<? extends Registry<T>> key();

    @Nullable
    Holder<T> getHolder(ResourceKey<T> key);

    @Nullable
    default Holder<T> getHolder(ResourceLocation id) {
        return getHolder(ResourceKey.create(key(), id));
    }

    default <R extends T> void listen(RegistrySupplier<R> supplier, Consumer<R> callback) {
        listen(supplier.getId(), obj -> callback.accept((R) obj));
    }

    void listen(ResourceLocation id, Consumer<T> callback);
}
