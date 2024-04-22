package com.mrmelon54.infrastructury.registry.level.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.function.Supplier;

public final class EntityAttributeRegistry {
    public static void register(Supplier<? extends EntityType<? extends LivingEntity>> type, Supplier<AttributeSupplier.Builder> attribute) {
        remapped.architectury.registry.level.entity.EntityAttributeRegistry.register(type, attribute);
    }
}
