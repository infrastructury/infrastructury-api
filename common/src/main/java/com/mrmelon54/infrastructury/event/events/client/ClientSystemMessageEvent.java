package com.mrmelon54.infrastructury.event.events.client;

#if MC_VER > MC_1_19_2
import com.mrmelon54.infrastructury.event.CompoundEventResult;
import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public interface ClientSystemMessageEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientSystemMessageEvent {
    }

    Event<Received> RECEIVED = EventWrapper.of(Inner.RECEIVED, received -> component -> CompoundEventResult.map(received.process(component)));

    @Environment(EnvType.CLIENT)
    interface Received {
        CompoundEventResult<Component> process(Component message);
    }
}
#endif
