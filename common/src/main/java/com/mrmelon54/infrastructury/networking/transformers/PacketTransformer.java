package com.mrmelon54.infrastructury.networking.transformers;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import com.mrmelon54.infrastructury.utils.Mapper;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public interface PacketTransformer {
    void inbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, NetworkManager.PacketContext context, TransformationSink sink);

    void outbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, TransformationSink sink);

    @FunctionalInterface
    interface TransformationSink {
        void accept(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf);

        static TransformationSink convert(remapped.architectury.networking.transformers.PacketTransformer.TransformationSink transformationSink) {
            return (side, id, buf) -> transformationSink.accept(side.architectury$side(), id, buf);
        }

        static remapped.architectury.networking.transformers.PacketTransformer.TransformationSink convert(TransformationSink sink) {
            return (side, resourceLocation, friendlyByteBuf) -> sink.accept(NetworkManager.Side.convert(side), resourceLocation, friendlyByteBuf);
        }
    }

    static PacketTransformer none() {
        return new PacketTransformer() {
            @Override
            public void inbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, NetworkManager.PacketContext context, TransformationSink sink) {
                sink.accept(side, id, buf);
            }

            @Override
            public void outbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, TransformationSink sink) {
                sink.accept(side, id, buf);
            }
        };
    }

    static PacketTransformer concat(Iterable<? extends PacketTransformer> transformers) {
        return PacketTransformer.convert(remapped.architectury.networking.transformers.PacketTransformer.concat(Mapper.map(transformers, PacketTransformer::convert)));
    }

    static PacketTransformer convert(remapped.architectury.networking.transformers.PacketTransformer packetTransformer) {
        return new PacketTransformer() {
            @Override
            public void inbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, NetworkManager.PacketContext context, TransformationSink sink) {
                packetTransformer.inbound(side.architectury$side(), id, buf, context.architectury$convert(), TransformationSink.convert(sink));
            }

            @Override
            public void outbound(NetworkManager.Side side, ResourceLocation id, FriendlyByteBuf buf, TransformationSink sink) {
                packetTransformer.outbound(side.architectury$side(), id, buf, TransformationSink.convert(sink));
            }
        };
    }

    default remapped.architectury.networking.transformers.PacketTransformer convert() {
        return new remapped.architectury.networking.transformers.PacketTransformer() {
            @Override
            public void inbound(remapped.architectury.networking.NetworkManager.Side side, ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf, remapped.architectury.networking.NetworkManager.PacketContext packetContext, TransformationSink transformationSink) {
                PacketTransformer.this.inbound(NetworkManager.Side.convert(side), resourceLocation, friendlyByteBuf, NetworkManager.PacketContext.convert(packetContext), PacketTransformer.TransformationSink.convert(transformationSink));
            }

            @Override
            public void outbound(remapped.architectury.networking.NetworkManager.Side side, ResourceLocation resourceLocation, FriendlyByteBuf friendlyByteBuf, TransformationSink transformationSink) {
                PacketTransformer.this.outbound(NetworkManager.Side.convert(side), resourceLocation, friendlyByteBuf, PacketTransformer.TransformationSink.convert(transformationSink));
            }
        };
    }
}
