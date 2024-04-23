package com.mrmelon54.infrastructury.networking.transformers;

import net.minecraft.network.protocol.Packet;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class PacketCollector implements PacketSink {
    @Nullable
    private final Consumer<Packet<?>> consumer;
    private final List<Packet<?>> packets = new ArrayList<>();

    public PacketCollector(@Nullable Consumer<Packet<?>> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void accept(Packet<?> packet) {
        packets.add(packet);
        if (this.consumer != null) this.consumer.accept(packet);
    }

    public List<Packet<?>> collect() {
        return packets;
    }
}
