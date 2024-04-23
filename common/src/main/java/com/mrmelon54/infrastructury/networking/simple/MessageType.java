package com.mrmelon54.infrastructury.networking.simple;

import com.mrmelon54.infrastructury.networking.NetworkManager;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public final class MessageType {
    private final SimpleNetworkManager manager;
    private final ResourceLocation id;
    private final NetworkManager.Side side;

    MessageType(SimpleNetworkManager manager, ResourceLocation id, NetworkManager.Side side) {
        this.manager = manager;
        this.id = id;
        this.side = side;
    }

    public SimpleNetworkManager getManager() {
        return manager;
    }

    public ResourceLocation getId() {
        return id;
    }

    public NetworkManager.Side getSide() {
        return side;
    }

    @Override
    public String toString() {
        return id.toString() + ":" + side.name().toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageType messageType = (MessageType) o;
        return id.equals(messageType.id) && side.equals(messageType.side);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, side);
    }
}
