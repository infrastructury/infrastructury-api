package net.minecraft.commands;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public interface CommandBuildContext {
    <T> HolderLookup<T> holderLookup(ResourceKey<? extends Registry<T>> var1);
}
