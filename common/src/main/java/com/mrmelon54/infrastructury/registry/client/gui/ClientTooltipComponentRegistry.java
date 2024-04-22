package com.mrmelon54.infrastructury.registry.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public final class ClientTooltipComponentRegistry {
    private ClientTooltipComponentRegistry() {
    }

    public static <T extends TooltipComponent> void register(Class<T> clazz, Function<? super T, ? extends ClientTooltipComponent> factory) {
        remapped.architectury.registry.client.gui.ClientTooltipComponentRegistry.register(clazz, factory);
    }
}
