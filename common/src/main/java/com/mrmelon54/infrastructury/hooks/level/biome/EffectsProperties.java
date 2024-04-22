package com.mrmelon54.infrastructury.hooks.level.biome;

import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.AmbientAdditionsSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.AmbientParticleSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.OptionalInt;

public interface EffectsProperties {
    remapped.architectury.hooks.level.biome.EffectsProperties architectury$convert();

    int getFogColor();

    int getWaterColor();

    int getWaterFogColor();

    int getSkyColor();

    OptionalInt getFoliageColorOverride();

    OptionalInt getGrassColorOverride();

    BiomeSpecialEffects.GrassColorModifier getGrassColorModifier();

    Optional<AmbientParticleSettings> getAmbientParticle();

    Optional<Holder<SoundEvent>> getAmbientLoopSound();

    Optional<AmbientMoodSettings> getAmbientMoodSound();

    Optional<AmbientAdditionsSettings> getAmbientAdditionsSound();

    Optional<Music> getBackgroundMusic();

    interface Mutable extends EffectsProperties {
        remapped.architectury.hooks.level.biome.EffectsProperties.Mutable architectury$convert();

        Mutable setFogColor(int var1);

        Mutable setWaterColor(int var1);

        Mutable setWaterFogColor(int var1);

        Mutable setSkyColor(int var1);

        Mutable setFoliageColorOverride(@Nullable Integer var1);

        Mutable setGrassColorOverride(@Nullable Integer var1);

        Mutable setGrassColorModifier(BiomeSpecialEffects.GrassColorModifier var1);

        Mutable setAmbientParticle(@Nullable AmbientParticleSettings var1);

        Mutable setAmbientLoopSound(@Nullable Holder<SoundEvent> var1);

        Mutable setAmbientMoodSound(@Nullable AmbientMoodSettings var1);

        Mutable setAmbientAdditionsSound(@Nullable AmbientAdditionsSettings var1);

        Mutable setBackgroundMusic(@Nullable Music var1);

        static Mutable convert(remapped.architectury.hooks.level.biome.EffectsProperties.Mutable mutable) {
            return new Mutable() {
                @Override
                public remapped.architectury.hooks.level.biome.EffectsProperties.Mutable architectury$convert() {
                    return mutable;
                }

                @Override
                public int getFogColor() {
                    return mutable.getFogColor();
                }

                @Override
                public int getWaterColor() {
                    return mutable.getWaterColor();
                }

                @Override
                public int getWaterFogColor() {
                    return mutable.getWaterFogColor();
                }

                @Override
                public int getSkyColor() {
                    return mutable.getSkyColor();
                }

                @Override
                public OptionalInt getFoliageColorOverride() {
                    return mutable.getFoliageColorOverride();
                }

                @Override
                public OptionalInt getGrassColorOverride() {
                    return mutable.getGrassColorOverride();
                }

                @Override
                public BiomeSpecialEffects.GrassColorModifier getGrassColorModifier() {
                    return mutable.getGrassColorModifier();
                }

                @Override
                public Optional<AmbientParticleSettings> getAmbientParticle() {
                    return mutable.getAmbientParticle();
                }

                @Override
                public Optional<Holder<SoundEvent>> getAmbientLoopSound() {
                    return mutable.getAmbientLoopSound();
                }

                @Override
                public Optional<AmbientMoodSettings> getAmbientMoodSound() {
                    return mutable.getAmbientMoodSound();
                }

                @Override
                public Optional<AmbientAdditionsSettings> getAmbientAdditionsSound() {
                    return mutable.getAmbientAdditionsSound();
                }

                @Override
                public Optional<Music> getBackgroundMusic() {
                    return mutable.getBackgroundMusic();
                }

                @Override
                public Mutable setFogColor(int var1) {
                    mutable.setFogColor(var1);
                    return this;
                }

                @Override
                public Mutable setWaterColor(int var1) {
                    mutable.setWaterColor(var1);
                    return this;
                }

                @Override
                public Mutable setWaterFogColor(int var1) {
                    mutable.setWaterFogColor(var1);
                    return this;
                }

                @Override
                public Mutable setSkyColor(int var1) {
                    mutable.setSkyColor(var1);
                    return this;
                }

                @Override
                public Mutable setFoliageColorOverride(@Nullable Integer var1) {
                    mutable.setFoliageColorOverride(var1);
                    return this;
                }

                @Override
                public Mutable setGrassColorOverride(@Nullable Integer var1) {
                    mutable.setGrassColorOverride(var1);
                    return this;
                }

                @Override
                public Mutable setGrassColorModifier(BiomeSpecialEffects.GrassColorModifier var1) {
                    mutable.setGrassColorModifier(var1);
                    return this;
                }

                @Override
                public Mutable setAmbientParticle(@Nullable AmbientParticleSettings var1) {
                    mutable.setAmbientParticle(var1);
                    return this;
                }

                @Override
                public Mutable setAmbientLoopSound(@Nullable Holder<SoundEvent> var1) {
                    mutable.setAmbientLoopSound(var1);
                    return this;
                }

                @Override
                public Mutable setAmbientMoodSound(@Nullable AmbientMoodSettings var1) {
                    mutable.setAmbientMoodSound(var1);
                    return this;
                }

                @Override
                public Mutable setAmbientAdditionsSound(@Nullable AmbientAdditionsSettings var1) {
                    mutable.setAmbientAdditionsSound(var1);
                    return this;
                }

                @Override
                public Mutable setBackgroundMusic(@Nullable Music var1) {
                    mutable.setBackgroundMusic(var1);
                    return this;
                }
            };
        }
    }

    static EffectsProperties convert(remapped.architectury.hooks.level.biome.EffectsProperties effectsProperties) {
        return new EffectsProperties() {
            @Override
            public remapped.architectury.hooks.level.biome.EffectsProperties architectury$convert() {
                return effectsProperties;
            }

            @Override
            public int getFogColor() {
                return effectsProperties.getFogColor();
            }

            @Override
            public int getWaterColor() {
                return effectsProperties.getWaterColor();
            }

            @Override
            public int getWaterFogColor() {
                return effectsProperties.getWaterFogColor();
            }

            @Override
            public int getSkyColor() {
                return effectsProperties.getSkyColor();
            }

            @Override
            public OptionalInt getFoliageColorOverride() {
                return effectsProperties.getFoliageColorOverride();
            }

            @Override
            public OptionalInt getGrassColorOverride() {
                return effectsProperties.getGrassColorOverride();
            }

            @Override
            public BiomeSpecialEffects.GrassColorModifier getGrassColorModifier() {
                return effectsProperties.getGrassColorModifier();
            }

            @Override
            public Optional<AmbientParticleSettings> getAmbientParticle() {
                return effectsProperties.getAmbientParticle();
            }

            @Override
            public Optional<Holder<SoundEvent>> getAmbientLoopSound() {
                return effectsProperties.getAmbientLoopSound();
            }

            @Override
            public Optional<AmbientMoodSettings> getAmbientMoodSound() {
                return effectsProperties.getAmbientMoodSound();
            }

            @Override
            public Optional<AmbientAdditionsSettings> getAmbientAdditionsSound() {
                return effectsProperties.getAmbientAdditionsSound();
            }

            @Override
            public Optional<Music> getBackgroundMusic() {
                return effectsProperties.getBackgroundMusic();
            }
        };
    }
}
