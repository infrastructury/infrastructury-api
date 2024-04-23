package com.mrmelon54.infrastructury.registry;

import com.mrmelon54.infrastructury.registry.registries.DeferredSupplier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import remapped.architectury.registry.CreativeTabOutput; // TODO: replace this

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class CreativeTabRegistry {
    private CreativeTabRegistry() {
    }

    public static CreativeModeTab create(Component title, Supplier<ItemStack> icon) {
        return create(builder -> {
            builder.title(title);
            builder.icon(icon);
        });
    }

    public static CreativeModeTab create(Consumer<CreativeModeTab.Builder> callback) {
        return remapped.architectury.registry.CreativeTabRegistry.create(callback);
    }

    public static DeferredSupplier<CreativeModeTab> ofBuiltin(CreativeModeTab tab) {
        return DeferredSupplier.of(remapped.architectury.registry.CreativeTabRegistry.ofBuiltin(tab));
    }

    public static DeferredSupplier<CreativeModeTab> defer(ResourceLocation name) {
        return DeferredSupplier.of(remapped.architectury.registry.CreativeTabRegistry.defer(name));
    }

    public static DeferredSupplier<CreativeModeTab> defer(ResourceKey<CreativeModeTab> name) {
        return defer(name.location());
    }

    public static void modifyBuiltin(CreativeModeTab tab, ModifyTabCallback filler) {
        modify(ofBuiltin(tab), filler);
    }

    public static void modify(DeferredSupplier<CreativeModeTab> tab, ModifyTabCallback filler) {
        remapped.architectury.registry.CreativeTabRegistry.modify(tab.back(), filler::accept);
    }

    public static void appendBuiltin(CreativeModeTab tab, ItemLike... items) {
        appendInternal(ofBuiltin(tab), Stream.of(items).map(ItemStack::new));
    }

    public static void appendBuiltinStream(CreativeModeTab tab, Stream<ItemStack> items) {
        appendInternal(ofBuiltin(tab), items);
    }

    public static void appendBuiltinSupply(CreativeModeTab tab, Stream<Supplier<ItemStack>> items) {
        appendInternalSupply(ofBuiltin(tab), items);
    }

    public static void append(DeferredSupplier<CreativeModeTab> tab, ItemLike... items) {
        appendInternal(tab, Stream.of(items).map(ItemStack::new));
    }

    public static void appendStream(DeferredSupplier<CreativeModeTab> tab, Stream<ItemStack> items) {
        appendInternal(tab, items);
    }

    public static void appendSupply(DeferredSupplier<CreativeModeTab> tab, Stream<Supplier<ItemStack>> items) {
        appendInternalSupply(tab, items);
    }

    public static void append(ResourceKey<CreativeModeTab> tab, ItemLike... items) {
        appendInternal(defer(tab), Stream.of(items).map(ItemStack::new));
    }

    public static void appendStream(ResourceKey<CreativeModeTab> tab, Stream<ItemStack> items) {
        appendInternal(defer(tab), items);
    }

    public static void appendSupply(ResourceKey<CreativeModeTab> tab, Stream<Supplier<ItemStack>> items) {
        appendInternalSupply(defer(tab), items);
    }

    // internal calls

    private static void appendInternal(DeferredSupplier<CreativeModeTab> tab, Stream<ItemStack> items) {
        appendInternalSupply(tab, items.map(itemStack -> () -> itemStack));
    }

    private static void appendInternalSupply(DeferredSupplier<CreativeModeTab> tab, Stream<Supplier<ItemStack>> items) {
        items.forEach(itemStackSupplier -> remapped.architectury.registry.CreativeTabRegistry.appendStack(tab.back(), itemStackSupplier));
    }

    @FunctionalInterface
    public interface ModifyTabCallback {
        void accept(FeatureFlagSet flags, CreativeTabOutput output, boolean canUseGameMasterBlocks);
    }
}
