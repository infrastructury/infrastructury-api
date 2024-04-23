package com.mrmelon54.infrastructury.event.events.client;

import com.mrmelon54.infrastructury.event.CompoundEventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import com.mrmelon54.infrastructury.event.PartialEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public interface ClientSystemMessageEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientSystemMessageEvent {
    }

    PartialEvent<Received> RECEIVED = EventWrapper.partial(() -> {
        #if MC_VER > MC_1_19_2
        return EventWrapper.of(Inner.RECEIVED, received -> component -> CompoundEventResult.map(received.process(component)));
        #else
        return null;
        #endif
    });

    @Environment(EnvType.CLIENT)
    interface Received {
        CompoundEventResult<Component> process(Component message);
    }
}
