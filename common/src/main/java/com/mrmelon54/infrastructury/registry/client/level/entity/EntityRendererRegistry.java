package com.mrmelon54.infrastructury.registry.client.level.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistry {
    private EntityRendererRegistry() {
    }

    public static <T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        remapped.architectury.registry.client.level.entity.EntityRendererRegistry.register(type, provider);
    }
}
