package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import com.mrmelon54.OmniPlay.hooks.client.screen.ScreenAccess;
import com.mrmelon54.OmniPlay.util.Graphics;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import remapped.architectury.event.Event;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface ClientGuiEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientGuiEvent {
    }

    Event<RenderHud> RENDER_HUD = EventWrapper.of(Inner.RENDER_HUD, x -> (graphics, tickDelta) -> x.renderHud(Graphics.get(graphics), tickDelta));
    Event<DebugText> DEBUG_TEXT_LEFT = EventWrapper.of(Inner.DEBUG_TEXT_LEFT, x -> x::gatherText);
    Event<DebugText> DEBUG_TEXT_RIGHT = EventWrapper.of(Inner.DEBUG_TEXT_RIGHT, x -> x::gatherText);
    #if MC_VER == MC_1_16_5
    Event<ScreenInitPre> INIT_PRE = EventWrapper.of(Inner.INIT_PRE, x -> (screen, a, b) -> EventResult.map(x.init(screen, () -> screen)));
    Event<ScreenInitPost> INIT_POST = EventWrapper.of(Inner.INIT_POST, x -> (screen, a, b) -> x.init(screen, () -> screen));
    #else
    Event<ScreenInitPre> INIT_PRE = EventWrapper.of(Inner.INIT_PRE, x -> (screen, screenAccess) -> EventResult.map(x.init(screen, screenAccess::getScreen)));
    Event<ScreenInitPost> INIT_POST = EventWrapper.of(Inner.INIT_POST, x -> (screen, screenAccess) -> x.init(screen, screenAccess::getScreen));
    #endif
    Event<ScreenRenderPre> RENDER_PRE = EventWrapper.of(Inner.RENDER_PRE, x -> (screen, graphics, mouseX, mouseY, delta) -> EventResult.map(x.render(screen, Graphics.get(graphics), mouseX, mouseY, delta)));
    Event<ScreenRenderPost> RENDER_POST = EventWrapper.of(Inner.RENDER_POST, x -> (screen, poseStack, i, i1, v) -> x.render(screen, Graphics.get(poseStack), i, i1, v));
    #if MC_VER > MC_1_17_1
    Event<ContainerScreenRenderBackground> RENDER_CONTAINER_BACKGROUND = EventWrapper.of(Inner.RENDER_CONTAINER_BACKGROUND, x -> (screen, graphics, mouseX, mouseY, delta) -> x.render(screen, Graphics.get(graphics), mouseX, mouseY, delta));
    Event<ContainerScreenRenderForeground> RENDER_CONTAINER_FOREGROUND = EventWrapper.of(Inner.RENDER_CONTAINER_FOREGROUND, x -> (screen, graphics, mouseX, mouseY, delta) -> x.render(screen, Graphics.get(graphics), mouseX, mouseY, delta));
    #endif
    Event<SetScreen> SET_SCREEN = EventWrapper.of(Inner.SET_SCREEN, x -> screen -> CompoundEventResult.map(x.modifyScreen(screen)));

    @Environment(EnvType.CLIENT)
    interface RenderHud {
        void renderHud(GuiGraphics graphics, float tickDelta);
    }

    @Environment(EnvType.CLIENT)
    interface DebugText {
        void gatherText(List<String> strings);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenInitPre {
        EventResult init(Screen screen, ScreenAccess access);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenInitPost {
        void init(Screen screen, ScreenAccess access);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenRenderPre {
        EventResult render(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenRenderPost {
        void render(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ContainerScreenRenderBackground {
        void render(AbstractContainerScreen<?> screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ContainerScreenRenderForeground {
        void render(AbstractContainerScreen<?> screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface SetScreen {
        CompoundEventResult<Screen> modifyScreen(Screen screen);
    }
}
