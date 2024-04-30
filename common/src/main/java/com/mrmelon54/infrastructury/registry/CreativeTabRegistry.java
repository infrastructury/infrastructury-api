package com.mrmelon54.infrastructury.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class CreativeTabRegistry {
    private CreativeTabRegistry() {
    }

    public static TabSupplier create(ResourceLocation key, Component title, Supplier<ItemStack> icon) {
        return create(key, builder -> {
            builder.title(title);
            builder.icon(icon);
        });
    }

    public static TabSupplier create(ResourceLocation key, Consumer<CreativeModeTab.Builder> callback) {
        return TabSupplier.convert(remapped.architectury.registry.CreativeTabRegistry.create(key, callback::accept));
    }

    public static TabSupplier ofBuiltin(CreativeModeTab tab) {
        #if MC_VER < MC_1_20_1
        return TabSupplier.convert(remapped.architectury.registry.CreativeTabRegistry.of(tab));
        #else
        remapped.architectury.registry.registries.DeferredSupplier<CreativeModeTab> tab2 = remapped.architectury.registry.CreativeTabRegistry.ofBuiltin(tab);
        return new TabSupplier() {
            @Override
            public ResourceLocation getName() {
                return tab2.getId();
            }

            @Override
            public boolean isPresent() {
                return true;
            }

            @Override
            public CreativeModeTab get() {
                return tab;
            }
        };
        #endif
    }

    public static TabSupplier defer(ResourceLocation name) {
        return TabSupplier.convert(remapped.architectury.registry.CreativeTabRegistry.defer(name));
    }

    public static TabSupplier defer(ResourceKey<CreativeModeTab> name) {
        return defer(name.location());
    }

    public static void modifyBuiltin(CreativeModeTab tab, ModifyTabCallback filler) {
        modify(ofBuiltin(tab), filler);
    }

    public static void modify(TabSupplier tab, ModifyTabCallback filler) {
        remapped.architectury.registry.CreativeTabRegistry.modify(tab.get(), (featureFlagSet, creativeTabOutput, b) -> filler.accept(featureFlagSet, CreativeTabOutput.convert(creativeTabOutput), b));
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

    public static void append(TabSupplier tab, ItemLike... items) {
        appendInternal(tab, Stream.of(items).map(ItemStack::new));
    }

    public static void appendStream(TabSupplier tab, Stream<ItemStack> items) {
        appendInternal(tab, items);
    }

    public static void appendSupply(TabSupplier tab, Stream<Supplier<ItemStack>> items) {
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

    private static void appendInternal(TabSupplier tab, Stream<ItemStack> items) {
        appendInternalSupply(tab, items.map(itemStack -> () -> itemStack));
    }

    private static void appendInternalSupply(TabSupplier tab, Stream<Supplier<ItemStack>> items) {
        items.forEach(itemStackSupplier -> remapped.architectury.registry.CreativeTabRegistry.appendStack(deferFromSupplier(tab), itemStackSupplier));
    }

    #if MC_VER < MC_1_20_1
    private static remapped.architectury.registry.CreativeTabRegistry.TabSupplier deferFromSupplier(TabSupplier tab) {
        return new remapped.architectury.registry.CreativeTabRegistry.TabSupplier() {
            @Override
            public ResourceLocation getName() {
                return tab.getName();
            }

            @Override
            public boolean isPresent() {
                return tab.isPresent();
            }

            @Override
            public CreativeModeTab get() {
                return tab.get();
            }
        };
    }
    #else
    private static DeferredSupplier<CreativeModeTab> deferFromSupplier(TabSupplier tab) {
        return new DeferredSupplier<>() {
            @Override
            public ResourceLocation getRegistryId() {
                return Registries.CREATIVE_MODE_TAB.location();
            }

            @Override
            public ResourceLocation getId() {
                return tab.getName();
            }

            @Override
            public boolean isPresent() {
                return tab.isPresent();
            }

            @Override
            public CreativeModeTab get() {
                return tab.get();
            }
        };
    }
    #endif

    public interface TabSupplier extends Supplier<CreativeModeTab> {
        ResourceLocation getName();

        boolean isPresent();

        #if MC_VER < MC_1_20_1
        static TabSupplier convert(remapped.architectury.registry.CreativeTabRegistry.TabSupplier tab) {

            return new TabSupplier() {
                @Override
                public ResourceLocation getName() {
                    return tab.getName();
                }

                @Override
                public boolean isPresent() {
                    return tab.isPresent();
                }

                @Override
                public CreativeModeTab get() {
                    return tab.get();
                }
            };
        }
        #else
        static TabSupplier convert(remapped.architectury.registry.registries.DeferredSupplier<CreativeModeTab> tab) {
            return new TabSupplier() {
                @Override
                public ResourceLocation getName() {
                    return tab2.getId();
                }

                @Override
                public boolean isPresent() {
                    return true;
                }

                @Override
                public CreativeModeTab get() {
                    return tab;
                }
            };
        }
        #endif
    }

    @FunctionalInterface
    public interface ModifyTabCallback {
        void accept(FeatureFlagSet flags, CreativeTabOutput output, boolean canUseGameMasterBlocks);
    }
}
