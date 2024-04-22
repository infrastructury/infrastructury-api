package com.mrmelon54.infrastructury.registry.client.level.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class EntityModelLayerRegistry {
    private EntityModelLayerRegistry() {
    }

    public static void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        remapped.architectury.registry.client.level.entity.EntityModelLayerRegistry.register(location, definition);
    }
}
