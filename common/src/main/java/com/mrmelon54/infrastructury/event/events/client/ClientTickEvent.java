package com.mrmelon54.infrastructury.event.events.client;

import com.mrmelon54.infrastructury.event.EventWrapper;
import remapped.architectury.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public interface ClientTickEvent<T> {
    interface Inner<T> extends remapped.architectury.event.events.client.ClientTickEvent<T> {
    }

    Event<Client> CLIENT_PRE = EventWrapper.of(Inner.CLIENT_PRE, client -> client::tick);
    Event<Client> CLIENT_POST = EventWrapper.of(Inner.CLIENT_POST, client -> client::tick);
    Event<ClientLevel> CLIENT_LEVEL_PRE = EventWrapper.of(Inner.CLIENT_LEVEL_PRE, clientLevel -> clientLevel::tick);
    Event<ClientLevel> CLIENT_LEVEL_POST = EventWrapper.of(Inner.CLIENT_LEVEL_POST, clientLevel -> clientLevel::tick);

    void tick(T instance);

    @Environment(EnvType.CLIENT)
    interface Client extends ClientTickEvent<Minecraft> {
    }

    @Environment(EnvType.CLIENT)
    interface ClientLevel extends ClientTickEvent<net.minecraft.client.multiplayer.ClientLevel> {
    }
}
