package com.mrmelon54.infrastructury.networking.transformers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;

@FunctionalInterface
public interface PacketSink {
    static PacketSink ofPlayer(ServerPlayer player) {
        return remapped.architectury.networking.transformers.PacketSink.ofPlayer(player)::accept;
    }

    static PacketSink ofPlayers(Iterable<? extends ServerPlayer> players) {
        return remapped.architectury.networking.transformers.PacketSink.ofPlayers(players)::accept;
    }

    @Environment(EnvType.CLIENT)
    static PacketSink client() {
        return remapped.architectury.networking.transformers.PacketSink.client()::accept;
    }

    void accept(Packet<?> packet);

    static remapped.architectury.networking.transformers.PacketSink convert(PacketSink sink) {
        return sink::accept;
    }
}
