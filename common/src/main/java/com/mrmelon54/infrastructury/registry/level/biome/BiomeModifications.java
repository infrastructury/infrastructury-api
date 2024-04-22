package com.mrmelon54.infrastructury.registry.level.biome;

import com.google.common.base.Predicates;
import com.mrmelon54.infrastructury.hooks.level.biome.BiomeProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public final class BiomeModifications {
    private BiomeModifications() {
    }

    public static void addProperties(BiConsumer<BiomeContext, BiomeProperties.Mutable> modifier) {
        addProperties(Predicates.alwaysTrue(), modifier);
    }

    public static void addProperties(Predicate<BiomeContext> predicate, BiConsumer<BiomeContext, BiomeProperties.Mutable> modifier) {
        remapped.architectury.registry.level.biome.BiomeModifications.addProperties(biomeContext -> predicate.test(BiomeContext.convert(biomeContext)), (biomeContext, mutable) -> modifier.accept(BiomeContext.convert(biomeContext), BiomeProperties.Mutable.convert(mutable)));
    }

    public interface BiomeContext {
        remapped.architectury.registry.level.biome.BiomeModifications.BiomeContext architectury$convert();

        Optional<ResourceLocation> getKey();

        BiomeProperties getProperties();

        boolean hasTag(TagKey<Biome> tag);

        static BiomeContext convert(remapped.architectury.registry.level.biome.BiomeModifications.BiomeContext biomeContext) {
            return new BiomeContext() {
                @Override
                public remapped.architectury.registry.level.biome.BiomeModifications.BiomeContext architectury$convert() {
                    return biomeContext;
                }

                @Override
                public Optional<ResourceLocation> getKey() {
                    return biomeContext.getKey();
                }

                @Override
                public BiomeProperties getProperties() {
                    return BiomeProperties.convert(biomeContext.getProperties());
                }

                @Override
                public boolean hasTag(TagKey<Biome> tag) {
                    return biomeContext.hasTag(tag);
                }
            };
        }
    }
}
