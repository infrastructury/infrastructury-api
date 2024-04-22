package com.mrmelon54.infrastructury.registry.level.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.function.Supplier;

public final class SpawnPlacementsRegistry {
    private SpawnPlacementsRegistry() {
    }

    public static <T extends Mob> void register(Supplier<? extends EntityType<T>> type, SpawnPlacements.Type spawnPlacement, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> spawnPredicate) {
        remapped.architectury.registry.level.entity.SpawnPlacementsRegistry.register(type, spawnPlacement, heightmapType, spawnPredicate);
    }
}
