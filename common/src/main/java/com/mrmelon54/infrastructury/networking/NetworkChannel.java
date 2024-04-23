package com.mrmelon54.infrastructury.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class NetworkChannel {
    private final remapped.architectury.networking.NetworkChannel channel;

    private NetworkChannel(remapped.architectury.networking.NetworkChannel channel) {
        this.channel = channel;
    }

    public static NetworkChannel create(ResourceLocation id) {
        return new NetworkChannel(remapped.architectury.networking.NetworkChannel.create(id));
    }

    public <T> void register(Class<T> type, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkManager.PacketContext>> messageConsumer) {
        channel.register(type, encoder, decoder, (t, packetContextSupplier) -> messageConsumer.accept(t, () -> NetworkManager.PacketContext.convert(packetContextSupplier.get())));
    }

    public static long hashCodeString(String str) {
        return remapped.architectury.networking.NetworkChannel.hashCodeString(str);
    }

    public <T> Packet<?> toPacket(NetworkManager.Side side, T message) {
        return channel.toPacket(side.architectury$side(), message);
    }

    public <T> void sendToPlayer(ServerPlayer player, T message) {
        channel.sendToPlayer(player, message);
    }

    public <T> void sendToPlayers(Iterable<ServerPlayer> players, T message) {
        channel.sendToPlayers(players, message);
    }

    @Environment(EnvType.CLIENT)
    public <T> void sendToServer(T message) {
        channel.sendToServer(message);
    }

    @Environment(EnvType.CLIENT)
    public <T> boolean canServerReceive(Class<T> type) {
        return channel.canServerReceive(type);
    }

    public <T> boolean canPlayerReceive(ServerPlayer player, Class<T> type) {
        return channel.canPlayerReceive(player, type);
    }
}
