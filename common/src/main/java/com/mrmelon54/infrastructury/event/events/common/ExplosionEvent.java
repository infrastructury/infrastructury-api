package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.List;

public interface ExplosionEvent {
    interface Inner extends remapped.architectury.event.events.common.ExplosionEvent {
    }

    Event<Pre> PRE = EventWrapper.of(Inner.PRE, pre -> (level, explosion) -> EventResult.map2(pre.explode(level, explosion)));
    Event<Detonate> DETONATE = EventWrapper.of(Inner.DETONATE, detonate -> detonate::explode);

    interface Pre {
        EventResult explode(Level level, Explosion explosion);
    }

    interface Detonate {
        void explode(Level level, Explosion explosion, List<Entity> affectedEntities);
    }
}
