package com.mrmelon54.infrastructury.networking.simple;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;

public abstract class BaseS2CMessage extends Message {
    private void sendTo(ServerPlayer player, Packet<?> packet) {
        if (player == null) throw new NullPointerException("Unable to send packet '" + getType().getId() + "' to a 'null' player!");
        player.connection.send(packet);
    }

    public final void sendTo(ServerPlayer player) {
        sendTo(player, toPacket());
    }

    public final void sendTo(Iterable<ServerPlayer> players) {
        Packet<?> packet = toPacket();
        players.forEach(player -> sendTo(player, packet));
    }

    public final void sendToAll(MinecraftServer server) {
        sendTo(server.getPlayerList().getPlayers());
    }

    public final void sendToLevel(ServerLevel level) {
        sendTo(level.players());
    }

    public final void sendToChunkListeners(LevelChunk chunk) {
        Packet<?> packet = toPacket();
        ((ServerChunkCache) chunk.getLevel().getChunkSource()).chunkMap.getPlayers(chunk.getPos(), false).forEach(player -> sendTo(player, packet));
    }
}
