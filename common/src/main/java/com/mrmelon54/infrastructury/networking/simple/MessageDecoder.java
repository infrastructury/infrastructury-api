package com.mrmelon54.infrastructury.networking.simple;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import net.minecraft.network.FriendlyByteBuf;

@FunctionalInterface
public interface MessageDecoder<T extends Message> {
    T decode(FriendlyByteBuf buf);

    default NetworkManager.NetworkReceiver createReceiver() {
        return (buf, context) -> {
            Message packet = decode(buf);
            context.queue(() -> packet.handle(context));
        };
    }
}
