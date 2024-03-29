package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.item.crafting.RecipeManager;
import remapped.architectury.event.Event;

@Environment(EnvType.CLIENT)
public interface ClientRecipeUpdateEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientRecipeUpdateEvent {
    }

    Event<ClientRecipeUpdateEvent> EVENT = EventWrapper.of(Inner.EVENT, clientRecipeUpdateEvent -> clientRecipeUpdateEvent::update);

    void update(RecipeManager recipeManager);
}
