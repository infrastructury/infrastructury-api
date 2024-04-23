package com.mrmelon54.infrastructury.networking;

import com.mrmelon54.infrastructury.networking.transformers.PacketSink;
import com.mrmelon54.infrastructury.networking.transformers.PacketTransformer;
import com.mrmelon54.infrastructury.utils.Env;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.Collections;
import java.util.List;

public final class NetworkManager {
    private NetworkManager() {
    }

    public static void registerReceiver(Side side, ResourceLocation id, NetworkReceiver receiver) {
        registerReceiver(side, id, Collections.emptyList(), receiver);
    }

    public static void registerReceiver(Side side, ResourceLocation id, List<PacketTransformer> packetTransformers, NetworkReceiver receiver) {
        remapped.architectury.networking.NetworkManager.registerReceiver(side.side, id, packetTransformers.stream().map(x -> x.convert()).toList(), receiver::receive);
    }

    public static void collectPackets(PacketSink sink, Side side, ResourceLocation id, FriendlyByteBuf buf) {
        remapped.architectury.networking.NetworkManager.collectPackets(PacketSink.convert(sink), side.side, id, buf);
    }

    public static void sendToPlayer(ServerPlayer player, ResourceLocation id, FriendlyByteBuf buf) {
        collectPackets(PacketSink.ofPlayer(player), serverToClient(), id, buf);
    }

    public static void sendToPlayers(Iterable<ServerPlayer> players, ResourceLocation id, FriendlyByteBuf buf) {
        collectPackets(PacketSink.ofPlayers(players), serverToClient(), id, buf);
    }

    @Environment(EnvType.CLIENT)
    public static void sendToServer(ResourceLocation id, FriendlyByteBuf buf) {
        collectPackets(PacketSink.client(), clientToServer(), id, buf);
    }

    @Environment(EnvType.CLIENT)
    public static boolean canServerReceive(ResourceLocation id) {
        return remapped.architectury.networking.NetworkManager.canServerReceive(id);
    }

    public static boolean canPlayerReceive(ServerPlayer player, ResourceLocation id) {
        return remapped.architectury.networking.NetworkManager.canPlayerReceive(player, id);
    }

    public static Packet<ClientGamePacketListener> createAddEntityPacket(Entity entity) {
        return remapped.architectury.networking.NetworkManager.createAddEntityPacket(entity);
    }

    public static Side serverToClient() {
        return Side.S2C;
    }

    public static Side clientToServer() {
        return Side.C2S;
    }

    public enum Side {
        S2C(remapped.architectury.networking.NetworkManager.Side.S2C),
        C2S(remapped.architectury.networking.NetworkManager.Side.C2S);

        private final remapped.architectury.networking.NetworkManager.Side side;

        Side(remapped.architectury.networking.NetworkManager.Side side) {
            this.side = side;
        }

        public static Side convert(remapped.architectury.networking.NetworkManager.Side side) {
            return side == remapped.architectury.networking.NetworkManager.Side.S2C ? S2C : C2S;
        }

        public remapped.architectury.networking.NetworkManager.Side architectury$side() {
            return side;
        }
    }

    @FunctionalInterface
    public interface NetworkReceiver {
        void receive(FriendlyByteBuf var1, remapped.architectury.networking.NetworkManager.PacketContext var2);
    }

    public interface PacketContext {
        remapped.architectury.networking.NetworkManager.PacketContext architectury$convert();

        Player getPlayer();

        void queue(Runnable var1);

        Env getEnvironment();

        default EnvType getEnv() {
            return this.getEnvironment().toPlatform();
        }

        static PacketContext convert(remapped.architectury.networking.NetworkManager.PacketContext packetContext) {
            return new PacketContext() {
                @Override
                public remapped.architectury.networking.NetworkManager.PacketContext architectury$convert() {
                    return packetContext;
                }

                @Override
                public Player getPlayer() {
                    return packetContext.getPlayer();
                }

                @Override
                public void queue(Runnable var1) {
                    packetContext.queue(var1);
                }

                @Override
                public Env getEnvironment() {
                    return Env.convert(packetContext.getEnvironment());
                }
            };
        }
    }
}
