package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.ChatTypePolyfill;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

@Environment(EnvType.CLIENT)
public interface ClientChatEvent {
    interface ChatEvent extends remapped.architectury.event.events.client.ClientChatEvent {
    }

    Event<Send> SEND = resolveSendEvent();

    static Event<Send> resolveSendEvent() {
        #if MC_VER < MC_1_19_2
        return EventWrapper.of(ChatEvent.PROCESS, send -> s -> CompoundEventResult.map(CompoundEventResult.fromEventResult(send.send(s, null))));
        #else
        return EventWrapper.of(ChatEvent.SEND, send -> (s, component) -> EventResult.map(send.send(s, component)));
        #endif
    }

    Event<Received> RECEIVED = resolveReceivedEvent();

    static Event<Received> resolveReceivedEvent() {
        #if MC_VER < MC_1_19_2
        return EventWrapper.of(ChatEvent.RECEIVED, received -> (bound, component, uuid) -> CompoundEventResult.map(received.process(new ChatType$Bound(bound), component)));
        #else
        return EventWrapper.of(ChatEvent.RECEIVED, received -> (bound, component) -> CompoundEventResult.map(received.process(new ChatTypePolyfill(bound), component)));
        #endif
    }

    @Environment(EnvType.CLIENT)
    interface Send {
        EventResult send(String message, @Nullable Component component);
    }

    @Environment(EnvType.CLIENT)
    interface Received {
        CompoundEventResult<Component> process(ChatTypePolyfill type, Component message);
    }
}
