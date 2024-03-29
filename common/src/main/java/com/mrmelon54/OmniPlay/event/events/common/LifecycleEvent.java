/*
 * This file is part of architectury.
 * Copyright (C) 2020, 2021, 2022 architectury
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.EventWrapper;
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
