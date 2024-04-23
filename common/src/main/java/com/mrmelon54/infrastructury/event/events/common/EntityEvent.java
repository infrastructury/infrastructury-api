package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import com.mrmelon54.infrastructury.event.PartialEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.jetbrains.annotations.Nullable;

public interface EntityEvent {
    interface Inner extends remapped.architectury.event.events.common.EntityEvent {
    }

    Event<LivingDeath> LIVING_DEATH = EventWrapper.of(Inner.LIVING_DEATH, livingDeath -> (entity, source) -> EventResult.map2(livingDeath.die(entity, source)));
    PartialEvent<LivingHurt> LIVING_HURT = EventWrapper.partial(() -> {
        #if MC_VER > MC_1_17_1
        return EventWrapper.of(Inner.LIVING_HURT, livingHurt -> (entity, source, amount) -> EventResult.map(livingHurt.hurt(entity, source, amount)));
        #else
        return null;
        #endif
    });
    Event<LivingCheckSpawn> LIVING_CHECK_SPAWN = EventWrapper.of(Inner.LIVING_CHECK_SPAWN, livingCheckSpawn -> (entity, world, x, y, z, type, spawner) -> EventResult.map(livingCheckSpawn.canSpawn(entity, world, x, y, z, type, spawner)));
    Event<Add> ADD = EventWrapper.of(Inner.ADD, add -> (entity, world) -> EventResult.map2(add.add(entity, world)));
    PartialEvent<EnterSection> ENTER_SECTION = EventWrapper.partial(() -> {
        #if MC_VER > MC_1_17_1
        return EventWrapper.of(Inner.ENTER_SECTION, enterSection -> enterSection::enterSection);
        #else
        return null;
        #endif
    });
    PartialEvent<AnimalTame> ANIMAL_TAME = EventWrapper.partial(() -> {
        #if MC_VER > MC_1_17_1
        return EventWrapper.of(Inner.ANIMAL_TAME, animalTame -> ((animal, player) -> EventResult.map(animalTame.tame(animal, player))));
        #else
        return null;
        #endif
    });

    interface LivingDeath {
        EventResult die(LivingEntity entity, DamageSource source);
    }

    interface LivingHurt {
        EventResult hurt(LivingEntity entity, DamageSource source, float amount);
    }

    interface LivingCheckSpawn {
        EventResult canSpawn(LivingEntity entity, LevelAccessor world, double x, double y, double z, MobSpawnType type, @Nullable BaseSpawner spawner);
    }

    interface Add {
        EventResult add(Entity entity, Level world);
    }

    interface EnterSection {
        void enterSection(Entity entity, int sectionX, int sectionY, int sectionZ, int prevX, int prevY, int prevZ);
    }

    interface AnimalTame {
        EventResult tame(Animal animal, Player player);
    }
}
