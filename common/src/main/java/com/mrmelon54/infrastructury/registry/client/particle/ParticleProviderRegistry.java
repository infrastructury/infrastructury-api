package com.mrmelon54.infrastructury.registry.client.particle;

import com.mrmelon54.infrastructury.registry.registries.RegistrySupplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.RandomSource;

import java.util.List;

@Environment(EnvType.CLIENT)
public final class ParticleProviderRegistry {
    private ParticleProviderRegistry() {
    }

    public interface ExtendedSpriteSet extends SpriteSet {
        TextureAtlas getAtlas();

        List<TextureAtlasSprite> getSprites();
    }

    public static <T extends ParticleOptions> void register(RegistrySupplier<? extends ParticleType<T>> supplier, ParticleProvider<T> provider) {
        supplier.listen(it -> register(it, provider));
    }

    public static <T extends ParticleOptions> void register(RegistrySupplier<? extends ParticleType<T>> supplier, DeferredParticleProvider<T> provider) {
        supplier.listen(it -> register(it, provider));
    }

    public static <T extends ParticleOptions> void register(ParticleType<T> type, ParticleProvider<T> provider) {
        remapped.architectury.registry.client.particle.ParticleProviderRegistry.register(type, provider);
    }

    public static <T extends ParticleOptions> void register(ParticleType<T> type, DeferredParticleProvider<T> provider) {
        remapped.architectury.registry.client.particle.ParticleProviderRegistry.register(type, extendedSpriteSet -> provider.create(new ExtendedSpriteSet() {
            @Override
            public TextureAtlas getAtlas() {
                return extendedSpriteSet.getAtlas();
            }

            @Override
            public List<TextureAtlasSprite> getSprites() {
                return extendedSpriteSet.getSprites();
            }

            @Override
            public TextureAtlasSprite get(int age, int lifetime) {
                return extendedSpriteSet.get(age, lifetime);
            }

            @Override
            public TextureAtlasSprite get(RandomSource random) {
                return extendedSpriteSet.get(random);
            }
        }));
    }

    @FunctionalInterface
    public interface DeferredParticleProvider<T extends ParticleOptions> {
        ParticleProvider<T> create(ExtendedSpriteSet spriteSet);
    }
}
