package com.mrmelon54.infrastructury.registry.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.Objects;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class ColorHandlerRegistry {
    private ColorHandlerRegistry() {
    }

    public static void registerItemColors(ItemColor color, ItemLike... items) {
        //noinspection unchecked
        Supplier<ItemLike>[] array = new Supplier[items.length];

        for (int i = 0; i < items.length; ++i) {
            if (items[i] == null)
                throw new RuntimeException("items[i] is null!");
            ItemLike item = items[i];
            array[i] = () -> item;
        }

        registerItemColors(color, array);
    }

    public static void registerBlockColors(BlockColor color, Block... blocks) {
        //noinspection unchecked
        Supplier<Block>[] array = new Supplier[blocks.length];

        for (int i = 0; i < blocks.length; ++i) {
            if (blocks[i] == null)
                throw new RuntimeException("blocks[i] is null!");
            Block block = blocks[i];
            array[i] = () -> block;
        }

        registerBlockColors(color, array);
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
