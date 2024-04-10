package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import remapped.architectury.event.Event;

import java.util.function.Function;

public interface LifecycleEvent {
    interface Inner extends remapped.architectury.event.events.common.LifecycleEvent {
    }

    Event<ServerState> SERVER_BEFORE_START = EventWrapper.of(Inner.SERVER_BEFORE_START, serverState -> serverState::stateChanged);
    Event<ServerState> SERVER_STARTING = EventWrapper.of(Inner.SERVER_STARTING, serverState -> serverState::stateChanged);
    Event<ServerState> SERVER_STARTED = EventWrapper.of(Inner.SERVER_STARTED, serverState -> serverState::stateChanged);
    Event<ServerState> SERVER_STOPPING = EventWrapper.of(Inner.SERVER_STOPPING, serverState -> serverState::stateChanged);
    Event<ServerState> SERVER_STOPPED = EventWrapper.of(Inner.SERVER_STOPPED, serverState -> serverState::stateChanged);
    Event<ServerLevelState> SERVER_LEVEL_LOAD = EventWrapper.of(Inner.SERVER_LEVEL_LOAD, serverLevelState -> serverLevelState::act);
    Event<ServerLevelState> SERVER_LEVEL_UNLOAD = EventWrapper.of(Inner.SERVER_LEVEL_UNLOAD, serverLevelState -> serverLevelState::act);
    Event<ServerLevelState> SERVER_LEVEL_SAVE = EventWrapper.of(Inner.SERVER_LEVEL_SAVE, serverLevelState -> serverLevelState::act);
    Event<Runnable> SETUP = EventWrapper.of(Inner.SETUP, Function.identity());

    interface InstanceState<T> {
        void stateChanged(T instance);
    }

    interface ServerState extends InstanceState<MinecraftServer> {
    }

    interface LevelState<T extends Level> {
        void act(T world);
    }

    interface ServerLevelState extends LevelState<ServerLevel> {
    }
}
