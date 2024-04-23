package com.mrmelon54.infrastructury.event.events.client;

import com.mrmelon54.infrastructury.event.CompoundEventResult;
import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.ChatTypePolyfill;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface ClientChatEvent {
    interface ChatEvent extends remapped.architectury.event.events.client.ClientChatEvent {
    }

    Event<Send> SEND = EventWrapper.of(ChatEvent.SEND, send -> {
        #if MC_VER < MC_1_19_2
        return s -> CompoundEventResult.map2(CompoundEventResult.fromEventResult(send.send(s, null)));
        #else
        return (s, component) -> EventResult.map(send.send(s, component));
        #endif
    });

    Event<Received> RECEIVED = EventWrapper.of(ChatEvent.RECEIVED, received -> {
        #if MC_VER < MC_1_19_2
        return (bound, component, uuid) -> CompoundEventResult.map2(received.process(new ChatTypePolyfill(bound), component));
        #else
        return (bound, component) -> CompoundEventResult.map(received.process(new ChatTypePolyfill(bound), component));
        #endif
    });

    @Environment(EnvType.CLIENT)
    interface Send {
        EventResult send(String message, @Nullable Component component);
    }

    @Environment(EnvType.CLIENT)
    interface Received {
        CompoundEventResult<Component> process(ChatTypePolyfill type, Component message);
    }
}
