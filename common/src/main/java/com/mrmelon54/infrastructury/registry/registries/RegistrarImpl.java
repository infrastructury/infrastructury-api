package com.mrmelon54.infrastructury.registry.registries;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RegistrarImpl<T> implements Registrar<T> {
    private final remapped.architectury.registry.registries.Registrar<T> r;

    RegistrarImpl(remapped.architectury.registry.registries.Registrar<T> r) {
        this.r = r;
    }

    @Override
    public RegistrySupplier<T> delegate(ResourceLocation id) {
        return RegistrySupplier.of(r.delegate(id));
    }

    @Override
    public <E extends T> RegistrySupplier<E> register(ResourceLocation id, Supplier<E> supplier) {
        return RegistrySupplier.of(r.register(id, supplier));
    }

    @Override
    public @Nullable ResourceLocation getId(T obj) {
        return r.getId(obj);
    }

    @Override
    public int getRawId(T obj) {
        return r.getRawId(obj);
    }

    @Override
    public Optional<ResourceKey<T>> getKey(T obj) {
        return r.getKey(obj);
    }

    @Override
    public @Nullable T get(ResourceLocation id) {
        return r.get(id);
    }

    @Override
    public @Nullable T byRawId(int rawId) {
        return r.byRawId(rawId);
    }

    @Override
    public boolean contains(ResourceLocation id) {
        return r.contains(id);
    }

    @Override
    public boolean containsValue(T obj) {
        return r.containsValue(obj);
    }

    @Override
    public Set<ResourceLocation> getIds() {
        return r.getIds();
    }

    @Override
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return r.entrySet();
    }

    @Override
    public ResourceKey<? extends Registry<T>> key() {
        return r.key();
    }

    @Override
    public @Nullable Holder<T> getHolder(ResourceKey<T> key) {
        #if MC_VER > MC_1_20_1
        return r.getHolder(key);
        #else
        return new Holder.Direct<>(r.delegate(key.location()).get());
        #endif
    }

    @Override
    public void listen(ResourceLocation id, Consumer<T> callback) {
        r.listen(id, callback);
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return r.iterator();
    }
}
