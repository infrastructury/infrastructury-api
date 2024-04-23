package com.mrmelon54.infrastructury.networking.transformers;

import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SinglePacketCollector implements PacketSink {
    @Nullable
    private final Consumer<Packet<?>> consumer;
    private Packet<?> packet;

    public SinglePacketCollector(@Nullable Consumer<Packet<?>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(Packet<?> packet) {
        if (this.packet != null) throw new IllegalStateException("Already accepted one packet!");
        this.packet = packet;
        if (this.consumer != null) this.consumer.accept(packet);
    }
}
