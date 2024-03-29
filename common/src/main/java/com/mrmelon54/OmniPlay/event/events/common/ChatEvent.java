package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

public interface ChatEvent {
    interface Inner extends remapped.architectury.event.events.common.ChatEvent {
    }

    Event<Decorate> DECORATE = EventWrapper.of(Inner.DECORATE, decorate -> (serverPlayer, chatComponent) -> decorate.decorate(serverPlayer, new ChatComponent() {
        @Override
        public Component get() {
            return chatComponent.get();
        }

        @Override
        public void set(Component component) {
            chatComponent.set(component);
        }
    }));
    Event<Received> RECEIVED = EventWrapper.of(Inner.RECEIVED, received -> (serverPlayer, component) -> EventResult.map(received.received(serverPlayer, component)));

    @FunctionalInterface
    interface Decorate {
        void decorate(@Nullable ServerPlayer player, ChatComponent component);
    }

    @FunctionalInterface
    interface Received {
        EventResult received(@Nullable ServerPlayer player, Component component);
    }

    interface ChatComponent {
        Component get();

        void set(Component component);
    }
}
