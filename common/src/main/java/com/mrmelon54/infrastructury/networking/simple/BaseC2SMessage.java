package com.mrmelon54.infrastructury.networking.simple;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

public abstract class BaseC2SMessage extends Message {
    @Environment(EnvType.CLIENT)
    public final void sendToServer() {
        if (Minecraft.getInstance().getConnection() == null) throw new IllegalStateException("Unable to send packet to the server while not in game!");
        Minecraft.getInstance().getConnection().send(toPacket());
    }
}
