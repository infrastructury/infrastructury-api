package com.mrmelon54.infrastructury.registry.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ColorHandlerRegistry {
    private ColorHandlerRegistry() {
    }

    public static void registerItemColors(ItemColor color, ItemLike... items) {
        remapped.architectury.registry.client.rendering.ColorHandlerRegistry.registerItemColors(color, items);
    }

    public static void registerBlockColors(BlockColor color, Block... blocks) {
        remapped.architectury.registry.client.rendering.ColorHandlerRegistry.registerBlockColors(color, blocks);
    }

    @SafeVarargs
    public static void registerItemColors(ItemColor color, Supplier<? extends ItemLike>... items) {
        remapped.architectury.registry.client.rendering.ColorHandlerRegistry.registerItemColors(color, items);
    }

    @SafeVarargs
    public static void registerBlockColors(BlockColor color, Supplier<? extends Block>... blocks) {
        remapped.architectury.registry.client.rendering.ColorHandlerRegistry.registerBlockColors(color, blocks);
    }
}
