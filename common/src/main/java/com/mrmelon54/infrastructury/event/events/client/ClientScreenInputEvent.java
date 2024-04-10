package com.mrmelon54.infrastructury.event.events.client;

import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import remapped.architectury.event.Event;

@Environment(EnvType.CLIENT)
public interface ClientScreenInputEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientScreenInputEvent {
    }

    static remapped.architectury.event.events.client.ClientScreenInputEvent.MouseScrolled mapMouseScrolled(MouseScrolled mouseScrolled) {
        #if MC_VER < MC_1_20_2
        // only one scroll amount value exists in older versions
        return ((minecraft, screen, v, v1, v2) -> EventResult.map2(mouseScrolled.mouseScrolled(minecraft, screen, v, v1, v2, v2)));
        #else
        return ((minecraft, screen, v, v1, v2, v3) -> EventResult.map(mouseScrolled.mouseScrolled(minecraft, screen, v, v1, v2, v3)));
        #endif
    }

    Event<MouseScrolled> MOUSE_SCROLLED_PRE = EventWrapper.of(Inner.MOUSE_SCROLLED_PRE, ClientScreenInputEvent::mapMouseScrolled);
    Event<MouseScrolled> MOUSE_SCROLLED_POST = EventWrapper.of(Inner.MOUSE_SCROLLED_POST, ClientScreenInputEvent::mapMouseScrolled);
    Event<MouseClicked> MOUSE_CLICKED_PRE = EventWrapper.of(Inner.MOUSE_CLICKED_PRE, mouseClicked -> ((minecraft, screen, v, v1, i) -> EventResult.map2(mouseClicked.mouseClicked(minecraft, screen, v, v1, i))));
    Event<MouseClicked> MOUSE_CLICKED_POST = EventWrapper.of(Inner.MOUSE_CLICKED_POST, mouseClicked -> ((minecraft, screen, v, v1, i) -> EventResult.map2(mouseClicked.mouseClicked(minecraft, screen, v, v1, i))));
    Event<MouseReleased> MOUSE_RELEASED_PRE = EventWrapper.of(Inner.MOUSE_RELEASED_PRE, mouseReleased -> ((minecraft, screen, v, v1, i) -> EventResult.map2(mouseReleased.mouseReleased(minecraft, screen, v, v1, i))));
    Event<MouseReleased> MOUSE_RELEASED_POST = EventWrapper.of(Inner.MOUSE_RELEASED_POST, mouseReleased -> ((minecraft, screen, v, v1, i) -> EventResult.map2(mouseReleased.mouseReleased(minecraft, screen, v, v1, i))));
    Event<MouseDragged> MOUSE_DRAGGED_PRE = EventWrapper.of(Inner.MOUSE_DRAGGED_PRE, mouseDragged -> ((minecraft, screen, v, v1, i, v2, v3) -> EventResult.map2(mouseDragged.mouseDragged(minecraft, screen, v, v1, i, v2, v3))));
    Event<MouseDragged> MOUSE_DRAGGED_POST = EventWrapper.of(Inner.MOUSE_DRAGGED_POST, mouseDragged -> ((minecraft, screen, v, v1, i, v2, v3) -> EventResult.map2(mouseDragged.mouseDragged(minecraft, screen, v, v1, i, v2, v3))));
    Event<KeyTyped> CHAR_TYPED_PRE = EventWrapper.of(Inner.CHAR_TYPED_PRE, keyTyped -> ((minecraft, screen, c, i) -> EventResult.map2(keyTyped.charTyped(minecraft, screen, c, i))));
    Event<KeyTyped> CHAR_TYPED_POST = EventWrapper.of(Inner.CHAR_TYPED_POST, keyTyped -> ((minecraft, screen, c, i) -> EventResult.map2(keyTyped.charTyped(minecraft, screen, c, i))));
    Event<KeyPressed> KEY_PRESSED_PRE = EventWrapper.of(Inner.KEY_PRESSED_PRE, keyPressed -> ((minecraft, screen, i, i1, i2) -> EventResult.map2(keyPressed.keyPressed(minecraft, screen, i, i1, i2))));
    Event<KeyPressed> KEY_PRESSED_POST = EventWrapper.of(Inner.KEY_PRESSED_POST, keyPressed -> ((minecraft, screen, i, i1, i2) -> EventResult.map2(keyPressed.keyPressed(minecraft, screen, i, i1, i2))));
    Event<KeyReleased> KEY_RELEASED_PRE = EventWrapper.of(Inner.KEY_RELEASED_PRE, keyReleased -> ((minecraft, screen, i, i1, i2) -> EventResult.map2(keyReleased.keyReleased(minecraft, screen, i, i1, i2))));
    Event<KeyReleased> KEY_RELEASED_POST = EventWrapper.of(Inner.KEY_RELEASED_POST, keyReleased -> ((minecraft, screen, i, i1, i2) -> EventResult.map2(keyReleased.keyReleased(minecraft, screen, i, i1, i2))));

    interface KeyPressed {
        EventResult keyPressed(Minecraft client, Screen screen, int keyCode, int scanCode, int modifiers);
    }

    interface KeyReleased {
        EventResult keyReleased(Minecraft client, Screen screen, int keyCode, int scanCode, int modifiers);
    }

    interface KeyTyped {
        EventResult charTyped(Minecraft client, Screen screen, char character, int keyCode);
    }

    interface MouseScrolled {
        EventResult mouseScrolled(Minecraft client, Screen screen, double mouseX, double mouseY, double amountX, double amountY);
    }

    interface MouseReleased {
        EventResult mouseReleased(Minecraft client, Screen screen, double mouseX, double mouseY, int button);
    }

    interface MouseDragged {
        EventResult mouseDragged(Minecraft client, Screen screen, double mouseX1, double mouseY1, int button, double mouseX2, double mouseY2);
    }

    interface MouseClicked {
        EventResult mouseClicked(Minecraft client, Screen screen, double mouseX, double mouseY, int button);
    }
}
