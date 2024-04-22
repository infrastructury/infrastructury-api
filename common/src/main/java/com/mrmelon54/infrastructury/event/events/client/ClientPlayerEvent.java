package com.mrmelon54.infrastructury.event.events.client;

import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface ClientPlayerEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientPlayerEvent {
    }

    Event<ClientPlayerJoin> CLIENT_PLAYER_JOIN = EventWrapper.of(Inner.CLIENT_PLAYER_JOIN, clientPlayerJoin -> clientPlayerJoin::join);
    Event<ClientPlayerQuit> CLIENT_PLAYER_QUIT = EventWrapper.of(Inner.CLIENT_PLAYER_QUIT, clientPlayerQuit -> clientPlayerQuit::quit);
    Event<ClientPlayerRespawn> CLIENT_PLAYER_RESPAWN = EventWrapper.of(Inner.CLIENT_PLAYER_RESPAWN, clientPlayerRespawn -> clientPlayerRespawn::respawn);

    @Environment(EnvType.CLIENT)
    interface ClientPlayerJoin {
        void join(LocalPlayer player);
    }

    @Environment(EnvType.CLIENT)
    interface ClientPlayerQuit {
        void quit(@Nullable LocalPlayer player);
    }

    @Environment(EnvType.CLIENT)
    interface ClientPlayerRespawn {
        void respawn(LocalPlayer oldPlayer, LocalPlayer newPlayer);
    }
}
