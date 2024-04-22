package com.mrmelon54.infrastructury.registry.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

@Environment(EnvType.CLIENT)
public final class BlockEntityRendererRegistry {
    private BlockEntityRendererRegistry() {
    }

    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererProvider<? super T> provider) {
        remapped.architectury.registry.client.rendering.BlockEntityRendererRegistry.register(type, provider);
    }
}
