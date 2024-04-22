package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public interface TickEvent<T> {
    interface Inner<T> extends remapped.architectury.event.events.common.TickEvent<T> {
    }

    Event<Server> SERVER_PRE = EventWrapper.of(Inner.SERVER_PRE, server -> server::tick);
    Event<Server> SERVER_POST = EventWrapper.of(Inner.SERVER_POST, server -> server::tick);
    Event<ServerLevelTick> SERVER_LEVEL_PRE = EventWrapper.of(Inner.SERVER_LEVEL_PRE, serverLevelTick -> serverLevelTick::tick);
    Event<ServerLevelTick> SERVER_LEVEL_POST = EventWrapper.of(Inner.SERVER_LEVEL_POST, serverLevelTick -> serverLevelTick::tick);
    Event<Player> PLAYER_PRE = EventWrapper.of(Inner.PLAYER_PRE, player -> player::tick);
    Event<Player> PLAYER_POST = EventWrapper.of(Inner.PLAYER_POST, player -> player::tick);

    void tick(T instance);

    interface Server extends TickEvent<MinecraftServer> {
    }

    interface LevelTick<T extends Level> extends TickEvent<T> {
    }

    interface ServerLevelTick extends LevelTick<ServerLevel> {
    }

    interface Player extends TickEvent<net.minecraft.world.entity.player.Player> {
    }
}
