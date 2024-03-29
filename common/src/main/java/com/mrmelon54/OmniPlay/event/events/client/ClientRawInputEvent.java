package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import remapped.architectury.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
public interface ClientRawInputEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientRawInputEvent {
    }

    Event<MouseScrolled> MOUSE_SCROLLED = EventWrapper.of(Inner.MOUSE_SCROLLED, mouseScrolled -> (minecraft, v, v1) -> EventResult.map(mouseScrolled.mouseScrolled(minecraft, v, v1)));
    Event<MouseClicked> MOUSE_CLICKED_PRE = EventWrapper.of(Inner.MOUSE_CLICKED_PRE, mouseClicked -> (minecraft, i, i1, i2) -> EventResult.map(mouseClicked.mouseClicked(minecraft, i, i1, i2)));
    Event<MouseClicked> MOUSE_CLICKED_POST = EventWrapper.of(Inner.MOUSE_CLICKED_POST, mouseClicked -> (minecraft, i, i1, i2) -> EventResult.map(mouseClicked.mouseClicked(minecraft, i, i1, i2)));
    Event<KeyPressed> KEY_PRESSED = EventWrapper.of(Inner.KEY_PRESSED, keyPressed -> (minecraft, i, i1, i2, i3) -> EventResult.map(keyPressed.keyPressed(minecraft, i, i1, i2, i3)));

    interface KeyPressed {
        EventResult keyPressed(Minecraft client, int keyCode, int scanCode, int action, int modifiers);
    }

    interface MouseScrolled {
        EventResult mouseScrolled(Minecraft client, double amountX, double amountY);
    }

    interface MouseClicked {
        EventResult mouseClicked(Minecraft client, int button, int action, int mods);
    }
}
