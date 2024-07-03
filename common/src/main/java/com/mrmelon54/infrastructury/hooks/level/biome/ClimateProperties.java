package com.mrmelon54.infrastructury.hooks.level.biome;

#if MC_VER >= MC_1_18_2
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
                    #if MC_VER <= MC_1_19_2
                    return mutable.getPrecipitation() != Biome.Precipitation.NONE;
                    #else
                    return mutable.hasPrecipitation();
                    #endif
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
                    #if MC_VER <= MC_1_19_2
                    mutable.setPrecipitation(var1 ? Biome.Precipitation.RAIN : Biome.Precipitation.NONE);
                    #else
                    mutable.setHasPrecipitation(var1);
                    #endif
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
                #if MC_VER <= MC_1_19_2
                return climateProperties.getPrecipitation() != Biome.Precipitation.NONE;
                #else
                return climateProperties.hasPrecipitation();
                #endif
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
#endif
