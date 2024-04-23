package com.mrmelon54.infrastructury.networking.transformers;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class SplitPacketTransformer implements PacketTransformer {
    private final remapped.architectury.networking.transformers.SplitPacketTransformer splitPacketTransformer;

    public SplitPacketTransformer() {
        splitPacketTransformer = new remapped.architectury.networking.transformers.SplitPacketTransformer();
    }

    @Override
    public void inbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, NetworkManager.PacketContext context, TransformationSink sink) {
        splitPacketTransformer.inbound(side.architectury$side(), id, buf, context.architectury$convert(), TransformationSink.convert(sink));
    }

    @Override
    public void outbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, TransformationSink sink) {
        splitPacketTransformer.outbound(side.architectury$side(), id, buf, TransformationSink.convert(sink));
    }
}
