package com.mrmelon54.infrastructury.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class SpawnEntityPacket {
    private static final ResourceLocation PACKET_ID = new ResourceLocation("infrastructury", "spawn_entity_packet");

    public static Packet<ClientGamePacketListener> create(Entity entity) {
        return remapped.architectury.networking.SpawnEntityPacket.create(entity);
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        @Environment(EnvType.CLIENT)
        public static void register() {
            NetworkManager.registerReceiver(NetworkManager.Side.S2C, PACKET_ID, Client::receive);
        }

        @Environment(EnvType.CLIENT)
        public static void receive(FriendlyByteBuf buf, NetworkManager.PacketContext context) {
            remapped.architectury.networking.SpawnEntityPacket.Client.receive(buf, context.architectury$convert());
        }
    }
}
