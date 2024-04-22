package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface LightningEvent {
    interface Inner extends remapped.architectury.event.events.common.LightningEvent {
    }

    Event<Strike> STRIKE = EventWrapper.of(Inner.STRIKE, strike -> strike::onStrike);

    interface Strike {
        void onStrike(LightningBolt bolt, Level level, Vec3 pos, List<Entity> toStrike);
    }
}
