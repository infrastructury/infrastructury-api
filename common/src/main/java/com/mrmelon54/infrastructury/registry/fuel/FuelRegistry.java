package com.mrmelon54.infrastructury.registry.fuel;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public final class FuelRegistry {
    public static void register(int time, ItemLike... items) {
        remapped.architectury.registry.fuel.FuelRegistry.register(time, items);
    }

    public static int get(ItemStack stack) {
        return remapped.architectury.registry.fuel.FuelRegistry.get(stack);
    }
}
