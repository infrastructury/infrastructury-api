package com.mrmelon54.infrastructury.networking.simple;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import com.mrmelon54.infrastructury.networking.transformers.SinglePacketCollector;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;

public abstract class Message {
    Message() {
    }

    public abstract MessageType getType();

    public abstract void write(FriendlyByteBuf buf);

    public abstract void handle(NetworkManager.PacketContext context);

    public final Packet<?> toPacket() {
        FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        write(buf);
        SinglePacketCollector sink = new SinglePacketCollector(null);
        NetworkManager.collectPackets(sink, getType().getSide(), getType().getId(), buf);
        return sink.getPacket();
    }
}
