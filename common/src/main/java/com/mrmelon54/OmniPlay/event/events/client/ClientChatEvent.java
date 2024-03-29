package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

@Environment(EnvType.CLIENT)
public interface ClientChatEvent {
    interface ChatEvent extends remapped.architectury.event.events.client.ClientChatEvent {
    }

    Event<Send> SEND = EventWrapper.of(ChatEvent.SEND, send -> (s, component) -> EventResult.map(send.send(s, component)));

    Event<Received> RECEIVED = EventWrapper.of(ChatEvent.RECEIVED, received -> (bound, component) -> CompoundEventResult.map(received.process(bound, component)));

    @Environment(EnvType.CLIENT)
    interface Send {
        EventResult send(String message, @Nullable Component component);
    }

    @Environment(EnvType.CLIENT)
    interface Received {
        CompoundEventResult<Component> process(ChatType.Bound type, Component message);
    }
}
