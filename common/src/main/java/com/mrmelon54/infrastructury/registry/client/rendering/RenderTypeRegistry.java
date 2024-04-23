package com.mrmelon54.infrastructury.registry.client.rendering;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@Environment(EnvType.CLIENT)
public final class RenderTypeRegistry {
    private RenderTypeRegistry() {
    }

    public static void register(RenderType type, Block... blocks) {
        remapped.architectury.registry.client.rendering.RenderTypeRegistry.register(type, blocks);
    }

    public static void register(RenderType type, Fluid... fluids) {
        remapped.architectury.registry.client.rendering.RenderTypeRegistry.register(type, fluids);
    }
}
