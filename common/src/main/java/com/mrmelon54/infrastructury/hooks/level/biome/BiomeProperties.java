package com.mrmelon54.infrastructury.hooks.level.biome;

public interface BiomeProperties {
    remapped.architectury.hooks.level.biome.BiomeProperties architectury$convert();

    ClimateProperties getClimateProperties();

    EffectsProperties getEffectsProperties();

    GenerationProperties getGenerationProperties();

    SpawnProperties getSpawnProperties();

    interface Mutable extends BiomeProperties {
        remapped.architectury.hooks.level.biome.BiomeProperties.Mutable architectury$convert();

        ClimateProperties.Mutable getClimateProperties();

        EffectsProperties.Mutable getEffectsProperties();

        GenerationProperties.Mutable getGenerationProperties();

        SpawnProperties.Mutable getSpawnProperties();

        static Mutable convert(remapped.architectury.hooks.level.biome.BiomeProperties.Mutable mutable) {
            return new Mutable() {
                @Override
                public remapped.architectury.hooks.level.biome.BiomeProperties.Mutable architectury$convert() {
                    return mutable;
                }

                @Override
                public ClimateProperties.Mutable getClimateProperties() {
                    return ClimateProperties.Mutable.convert(mutable.getClimateProperties());
                }

                @Override
                public EffectsProperties.Mutable getEffectsProperties() {
                    return EffectsProperties.Mutable.convert(mutable.getEffectsProperties());
                }

                @Override
                public GenerationProperties.Mutable getGenerationProperties() {
                    return GenerationProperties.Mutable.convert(mutable.getGenerationProperties());
                }

                @Override
                public SpawnProperties.Mutable getSpawnProperties() {
                    return SpawnProperties.Mutable.convert(mutable.getSpawnProperties());
                }
            };
        }
    }

    static BiomeProperties convert(remapped.architectury.hooks.level.biome.BiomeProperties biomeProperties) {
        return new BiomeProperties() {
            @Override
            public remapped.architectury.hooks.level.biome.BiomeProperties architectury$convert() {
                return biomeProperties;
            }

            @Override
            public ClimateProperties getClimateProperties() {
                return ClimateProperties.convert(biomeProperties.getClimateProperties());
            }

            @Override
            public EffectsProperties getEffectsProperties() {
                return EffectsProperties.convert(biomeProperties.getEffectsProperties());
            }

            @Override
            public GenerationProperties getGenerationProperties() {
                return GenerationProperties.convert(biomeProperties.getGenerationProperties());
            }

            @Override
            public SpawnProperties getSpawnProperties() {
                return SpawnProperties.convert(biomeProperties.getSpawnProperties());
            }
        };
    }
}
