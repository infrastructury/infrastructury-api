package com.mrmelon54.infrastructury.hooks.level.biome;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

public interface GenerationProperties {
    remapped.architectury.hooks.level.biome.GenerationProperties architectury$convert();

    Iterable<Holder<ConfiguredWorldCarver<?>>> getCarvers(GenerationStep.Carving var1);

    Iterable<Holder<PlacedFeature>> getFeatures(GenerationStep.Decoration var1);

    List<Iterable<Holder<PlacedFeature>>> getFeatures();

    interface Mutable extends GenerationProperties {
        remapped.architectury.hooks.level.biome.GenerationProperties.Mutable architectury$convert();

        Mutable addFeature(GenerationStep.Decoration var1, Holder<PlacedFeature> var2);

        @ApiStatus.Experimental
        Mutable addFeature(GenerationStep.Decoration var1, ResourceKey<PlacedFeature> var2);

        Mutable addCarver(GenerationStep.Carving var1, Holder<ConfiguredWorldCarver<?>> var2);

        @ApiStatus.Experimental
        Mutable addCarver(GenerationStep.Carving var1, ResourceKey<ConfiguredWorldCarver<?>> var2);

        Mutable removeFeature(GenerationStep.Decoration var1, ResourceKey<PlacedFeature> var2);

        Mutable removeCarver(GenerationStep.Carving var1, ResourceKey<ConfiguredWorldCarver<?>> var2);

        static Mutable convert(remapped.architectury.hooks.level.biome.GenerationProperties.Mutable mutable) {
            return new Mutable() {
                @Override
                public remapped.architectury.hooks.level.biome.GenerationProperties.Mutable architectury$convert() {
                    return mutable;
                }

                @Override
                public Iterable<Holder<ConfiguredWorldCarver<?>>> getCarvers(GenerationStep.Carving var1) {
                    return mutable.getCarvers(var1);
                }

                @Override
                public Iterable<Holder<PlacedFeature>> getFeatures(GenerationStep.Decoration var1) {
                    return mutable.getFeatures(var1);
                }

                @Override
                public List<Iterable<Holder<PlacedFeature>>> getFeatures() {
                    return mutable.getFeatures();
                }

                @Override
                public Mutable addFeature(GenerationStep.Decoration var1, Holder<PlacedFeature> var2) {
                    mutable.addFeature(var1, var2);
                    return this;
                }

                @Override
                public Mutable addFeature(GenerationStep.Decoration var1, ResourceKey<PlacedFeature> var2) {
                    mutable.addFeature(var1, var2);
                    return this;
                }

                @Override
                public Mutable addCarver(GenerationStep.Carving var1, Holder<ConfiguredWorldCarver<?>> var2) {
                    mutable.addCarver(var1, var2);
                    return this;
                }

                @Override
                public Mutable addCarver(GenerationStep.Carving var1, ResourceKey<ConfiguredWorldCarver<?>> var2) {
                    mutable.addCarver(var1, var2);
                    return this;
                }

                @Override
                public Mutable removeFeature(GenerationStep.Decoration var1, ResourceKey<PlacedFeature> var2) {
                    mutable.removeFeature(var1, var2);
                    return this;
                }

                @Override
                public Mutable removeCarver(GenerationStep.Carving var1, ResourceKey<ConfiguredWorldCarver<?>> var2) {
                    mutable.removeCarver(var1, var2);
                    return this;
                }
            };
        }
    }

    static GenerationProperties convert(remapped.architectury.hooks.level.biome.GenerationProperties genericProperties) {
        return new GenerationProperties() {
            @Override
            public remapped.architectury.hooks.level.biome.GenerationProperties architectury$convert() {
                return genericProperties;
            }

            @Override
            public Iterable<Holder<ConfiguredWorldCarver<?>>> getCarvers(GenerationStep.Carving var1) {
                return genericProperties.getCarvers(var1);
            }

            @Override
            public Iterable<Holder<PlacedFeature>> getFeatures(GenerationStep.Decoration var1) {
                return genericProperties.getFeatures(var1);
            }

            @Override
            public List<Iterable<Holder<PlacedFeature>>> getFeatures() {
                return genericProperties.getFeatures();
            }
        };
    }
}
