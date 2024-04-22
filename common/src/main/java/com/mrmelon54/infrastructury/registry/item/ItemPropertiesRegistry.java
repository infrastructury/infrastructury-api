package com.mrmelon54.infrastructury.registry.item;

import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

public final class ItemPropertiesRegistry {
    public static ClampedItemPropertyFunction registerGeneric(ResourceLocation propertyId, ClampedItemPropertyFunction function) {
        return remapped.architectury.registry.item.ItemPropertiesRegistry.registerGeneric(propertyId, function);
    }

    public static ClampedItemPropertyFunction register(ItemLike item, ResourceLocation propertyId, ClampedItemPropertyFunction function) {
        return remapped.architectury.registry.item.ItemPropertiesRegistry.register(item, propertyId, function);
    }
}
