package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import com.mrmelon54.OmniPlay.event.events.common.LifecycleEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import remapped.architectury.event.Event;

@Environment(EnvType.CLIENT)
public interface ClientLifecycleEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientLifecycleEvent {
    }

    Event<ClientState> CLIENT_STARTED = EventWrapper.of(Inner.CLIENT_STARTED, clientState -> clientState::stateChanged);
    Event<ClientState> CLIENT_STOPPING = EventWrapper.of(Inner.CLIENT_STOPPING, clientState -> clientState::stateChanged);
    Event<ClientLevelState> CLIENT_LEVEL_LOAD = EventWrapper.of(Inner.CLIENT_LEVEL_LOAD, clientLevelState -> clientLevelState::act);
    Event<ClientState> CLIENT_SETUP = EventWrapper.of(Inner.CLIENT_SETUP, clientState -> clientState::stateChanged);

    @Environment(EnvType.CLIENT)
    interface ClientState extends LifecycleEvent.InstanceState<Minecraft> {
    }

    @Environment(EnvType.CLIENT)
    interface ClientLevelState extends LifecycleEvent.LevelState<ClientLevel> {
    }
}
