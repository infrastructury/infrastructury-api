package com.mrmelon54.infrastructury.networking.simple;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import com.mrmelon54.infrastructury.networking.transformers.PacketTransformer;
import com.mrmelon54.infrastructury.platform.Platform;
import com.mrmelon54.infrastructury.utils.Env;
import net.minecraft.resources.ResourceLocation;

#if JAVA_8
import java.util.ArrayList;
#endif
import java.util.List;

public class SimpleNetworkManager {
    public static SimpleNetworkManager create(String namespace) {
        return new SimpleNetworkManager(namespace);
    }

    private final String namespace;

    private SimpleNetworkManager(String namespace) {
        this.namespace = namespace;
    }

    public MessageType registerS2C(String id, MessageDecoder<BaseS2CMessage> decoder) {
        return registerS2C(id, decoder, #if JAVA_8 new ArrayList<>() #else List.of() #endif );
    }

    public MessageType registerS2C(String id, MessageDecoder<BaseS2CMessage> decoder, List<PacketTransformer> transformers) {
        MessageType messageType = new MessageType(this, new ResourceLocation(namespace, id), NetworkManager.Side.S2C);

        if (Platform.getEnvironment() == Env.CLIENT) {
            NetworkManager.NetworkReceiver receiver = decoder.createReceiver();
            NetworkManager.registerReceiver(NetworkManager.Side.S2C, messageType.getId(), transformers, receiver);
        }

        return messageType;
    }

    public MessageType registerC2S(String id, MessageDecoder<BaseS2CMessage> decoder) {
        return registerC2S(id, decoder, #if JAVA_8 new ArrayList<>() #else List.of() #endif );
    }

    public MessageType registerC2S(String id, MessageDecoder<BaseS2CMessage> decoder, List<PacketTransformer> transformers) {
        MessageType messageType = new MessageType(this, new ResourceLocation(namespace, id), NetworkManager.Side.C2S);
        NetworkManager.NetworkReceiver receiver = decoder.createReceiver();
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, messageType.getId(), transformers, receiver);
        return messageType;
    }
}
