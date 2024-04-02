package com.mrmelon54.OmniPlay.event.events.client;

#if MC_VER != MC_1_16_5
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.server.packs.resources.ResourceProvider;
import remapped.architectury.event.Event;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface ClientReloadShadersEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientReloadShadersEvent {
    }

    Event<ClientReloadShadersEvent> EVENT = EventWrapper.of(Inner.EVENT, clientReloadShadersEvent -> (resourceProvider, shadersSink) -> clientReloadShadersEvent.reload(resourceProvider, shadersSink::registerShader));

    void reload(ResourceProvider provider, ShadersSink sink);

    @FunctionalInterface
    interface ShadersSink {
        void registerShader(ShaderInstance shader, Consumer<ShaderInstance> callback);
    }
}
#endif
