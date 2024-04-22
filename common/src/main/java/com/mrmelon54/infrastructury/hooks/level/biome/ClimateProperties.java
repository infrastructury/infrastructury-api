package com.mrmelon54.infrastructury.hooks.level.biome;

import net.minecraft.world.level.biome.Biome;

public interface ClimateProperties {
    remapped.architectury.hooks.level.biome.ClimateProperties architectury$convert();

    boolean hasPrecipitation();

    float getTemperature();

    Biome.TemperatureModifier getTemperatureModifier();

    float getDownfall();

    interface Mutable extends ClimateProperties {
        remapped.architectury.hooks.level.biome.ClimateProperties.Mutable architectury$convert();

        Mutable setHasPrecipitation(boolean var1);

        Mutable setTemperature(float var1);

        Mutable setTemperatureModifier(Biome.TemperatureModifier var1);

        Mutable setDownfall(float var1);

        static Mutable convert(remapped.architectury.hooks.level.biome.ClimateProperties.Mutable mutable) {
            return new Mutable() {
                @Override
                public remapped.architectury.hooks.level.biome.ClimateProperties.Mutable architectury$convert() {
                    return mutable;
                }

                @Override
                public boolean hasPrecipitation() {
                    return mutable.hasPrecipitation();
                }

                @Override
                public float getTemperature() {
                    return mutable.getTemperature();
                }

                @Override
                public Biome.TemperatureModifier getTemperatureModifier() {
                    return mutable.getTemperatureModifier();
                }

                @Override
                public float getDownfall() {
                    return mutable.getDownfall();
                }

                @Override
                public Mutable setHasPrecipitation(boolean var1) {
                    mutable.setHasPrecipitation(var1);
                    return this;
                }

                @Override
                public Mutable setTemperature(float var1) {
                    mutable.setTemperature(var1);
                    return this;
                }

                @Override
                public Mutable setTemperatureModifier(Biome.TemperatureModifier var1) {
                    mutable.setTemperatureModifier(var1);
                    return this;
                }

                @Override
                public Mutable setDownfall(float var1) {
                    mutable.setDownfall(var1);
                    return this;
                }
            };
        }
    }

    static ClimateProperties convert(remapped.architectury.hooks.level.biome.ClimateProperties climateProperties) {
        return new ClimateProperties() {
            @Override
            public remapped.architectury.hooks.level.biome.ClimateProperties architectury$convert() {
                return climateProperties;
            }

            @Override
            public boolean hasPrecipitation() {
                return climateProperties.hasPrecipitation();
            }

            @Override
            public float getTemperature() {
                return climateProperties.getTemperature();
            }

            @Override
            public Biome.TemperatureModifier getTemperatureModifier() {
                return climateProperties.getTemperatureModifier();
            }

            @Override
            public float getDownfall() {
                return climateProperties.getDownfall();
            }
        };
    }
}
