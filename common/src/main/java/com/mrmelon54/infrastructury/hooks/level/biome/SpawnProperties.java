package com.mrmelon54.infrastructury.hooks.level.biome;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public interface SpawnProperties {
    remapped.architectury.hooks.level.biome.SpawnProperties architectury$convert();

    float getCreatureProbability();

    Map<MobCategory, List<MobSpawnSettings.SpawnerData>> getSpawners();

    Map<EntityType<?>, MobSpawnSettings.MobSpawnCost> getMobSpawnCosts();

    interface Mutable extends SpawnProperties {
        remapped.architectury.hooks.level.biome.SpawnProperties.Mutable architectury$convert();

        Mutable setCreatureProbability(float var1);

        Mutable addSpawn(MobCategory var1, MobSpawnSettings.SpawnerData var2);

        boolean removeSpawns(BiPredicate<MobCategory, MobSpawnSettings.SpawnerData> var1);

        Mutable setSpawnCost(EntityType<?> var1, MobSpawnSettings.MobSpawnCost var2);

        Mutable setSpawnCost(EntityType<?> var1, double var2, double var4);

        Mutable clearSpawnCost(EntityType<?> var1);

        static Mutable convert(remapped.architectury.hooks.level.biome.SpawnProperties.Mutable mutable) {
            return new Mutable() {

                @Override
                public remapped.architectury.hooks.level.biome.SpawnProperties.Mutable architectury$convert() {
                    return mutable;
                }

                @Override
                public float getCreatureProbability() {
                    return mutable.getCreatureProbability();
                }

                @Override
                public Map<MobCategory, List<MobSpawnSettings.SpawnerData>> getSpawners() {
                    return mutable.getSpawners();
                }

                @Override
                public Map<EntityType<?>, MobSpawnSettings.MobSpawnCost> getMobSpawnCosts() {
                    return mutable.getMobSpawnCosts();
                }

                @Override
                public Mutable setCreatureProbability(float var1) {
                    mutable.setCreatureProbability(var1);
                    return this;
                }

                @Override
                public Mutable addSpawn(MobCategory var1, MobSpawnSettings.SpawnerData var2) {
                    mutable.addSpawn(var1, var2);
                    return this;
                }

                @Override
                public boolean removeSpawns(BiPredicate<MobCategory, MobSpawnSettings.SpawnerData> var1) {
                    return mutable.removeSpawns(var1);
                }

                @Override
                public Mutable setSpawnCost(EntityType<?> var1, MobSpawnSettings.MobSpawnCost var2) {
                    mutable.setSpawnCost(var1, var2);
                    return this;
                }

                @Override
                public Mutable setSpawnCost(EntityType<?> var1, double var2, double var4) {
                    mutable.setSpawnCost(var1, var2, var4);
                    return this;
                }

                @Override
                public Mutable clearSpawnCost(EntityType<?> var1) {
                    mutable.clearSpawnCost(var1);
                    return this;
                }
            };
        }
    }

    static SpawnProperties convert(remapped.architectury.hooks.level.biome.SpawnProperties spawnProperties) {
        return new SpawnProperties() {
            @Override
            public remapped.architectury.hooks.level.biome.SpawnProperties architectury$convert() {
                return spawnProperties;
            }

            @Override
            public float getCreatureProbability() {
                return spawnProperties.getCreatureProbability();
            }

            @Override
            public Map<MobCategory, List<MobSpawnSettings.SpawnerData>> getSpawners() {
                return spawnProperties.getSpawners();
            }

            @Override
            public Map<EntityType<?>, MobSpawnSettings.MobSpawnCost> getMobSpawnCosts() {
                return spawnProperties.getMobSpawnCosts();
            }
        };
    }
}
