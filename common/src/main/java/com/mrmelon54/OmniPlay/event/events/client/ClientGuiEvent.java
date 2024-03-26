package com.mrmelon54.OmniPlay.event.events.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.Event;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import com.mrmelon54.OmniPlay.hooks.client.screen.ScreenAccess;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface ClientGuiEvent {
    interface GuiEvent extends remapped.architectury.event.events.client.ClientGuiEvent {
    }

    Event<RenderHud> RENDER_HUD = EventWrapper.of((Event<GuiEvent.RenderHud>) GuiEvent.RENDER_HUD, x -> (graphics, tickDelta) -> x.renderHud(getGuiGraphics(graphics), tickDelta));
    Event<DebugText> DEBUG_TEXT_LEFT = EventWrapper.of((Event<GuiEvent.DebugText>) GuiEvent.DEBUG_TEXT_LEFT, x -> x::gatherText);
    Event<DebugText> DEBUG_TEXT_RIGHT = EventWrapper.of((Event<GuiEvent.DebugText>) GuiEvent.DEBUG_TEXT_RIGHT, x -> x::gatherText);
    #if MC_VER == MC_1_16_5
    Event<ScreenInitPre> INIT_PRE = EventWrapper.of((Event<GuiEvent.ScreenInitPre>) GuiEvent.INIT_PRE, x -> (screen, a, b) -> EventResult.map(x.init(screen, () -> screen)));
    Event<ScreenInitPost> INIT_POST = EventWrapper.of((Event<GuiEvent.ScreenInitPost>) GuiEvent.INIT_POST, x -> (screen, a, b) -> x.init(screen, () -> screen));
    #else
    Event<ScreenInitPre> INIT_PRE = EventWrapper.of((Event<GuiEvent.ScreenInitPre>) GuiEvent.INIT_PRE, x -> (screen, screenAccess) -> EventResult.map(x.init(screen,getScreen(screenAccess))));
    Event<ScreenInitPost> INIT_POST = EventWrapper.of((Event<GuiEvent.ScreenInitPost>) GuiEvent.INIT_POST, x -> (screen, screenAccess) -> x.init(screen, screenAccess::getScreen));
    #endif
    Event<ScreenRenderPre> RENDER_PRE = EventWrapper.of((Event<GuiEvent.ScreenRenderPre>) GuiEvent.RENDER_PRE, x -> (screen, graphics, mouseX, mouseY, delta) -> EventResult.map(x.render(screen, getGuiGraphics(graphics), mouseX, mouseY, delta)));
    Event<ScreenRenderPost> RENDER_POST = EventWrapper.of((Event<GuiEvent.ScreenRenderPost>) GuiEvent.RENDER_POST, x -> (screen, poseStack, i, i1, v) -> x.render(screen, getGuiGraphics(poseStack), i, i1, v));
    #if MC_VER > MC_1_17_1
    Event<ContainerScreenRenderBackground> RENDER_CONTAINER_BACKGROUND = EventWrapper.of((Event<GuiEvent.ContainerScreenRenderBackground>) GuiEvent.RENDER_CONTAINER_BACKGROUND, x -> (screen, graphics, mouseX, mouseY, delta) -> x.render(screen, getGuiGraphics(graphics), mouseX, mouseY, delta));
    Event<ContainerScreenRenderForeground> RENDER_CONTAINER_FOREGROUND = EventWrapper.of((Event<GuiEvent.ContainerScreenRenderForeground>) GuiEvent.RENDER_CONTAINER_FOREGROUND, x -> (screen, graphics, mouseX, mouseY, delta) -> x.render(screen, getGuiGraphics(graphics), mouseX, mouseY, delta));
    #endif
    Event<SetScreen> SET_SCREEN = EventWrapper.of((Event<GuiEvent.SetScreen>) GuiEvent.SET_SCREEN, x -> screen -> CompoundEventResult.map(x.modifyScreen(screen)));

    #if MC_VER < MC_1_20_1
    static GuiGraphics getGuiGraphics(PoseStack matrices) {
        return new GuiGraphics(matrices);
    }
    #else
    static GuiGraphics getGuiGraphics(GuiGraphics guiGraphics) {
        return guiGraphics;
    }
    #endif

    @Environment(EnvType.CLIENT)
    interface RenderHud {
        /**
         * Invoked after the in-game hud has been rendered.
         * Equivalent to Forge's {@code RenderGameOverlayEvent.Post@ElementType#ALL} and Fabric's {@code HudRenderCallback}.
         *
         * @param graphics  The graphics context.
         * @param tickDelta The tick delta.
         */
        void renderHud(GuiGraphics graphics, float tickDelta);
    }

    @Environment(EnvType.CLIENT)
    interface DebugText {
        /**
         * Invoked when the debug text is being gathered for rendering.
         * There are two different versions of this event, one for the left and one for the right side.
         * Equivalent to Forge's {@code RenderGameOverlayEvent.Text}, when {@code Minecraft.getInstance().options.renderDebug} is true.
         *
         * @param strings The current debug text strings.
         */
        void gatherText(List<String> strings);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenInitPre {
        /**
         * Invoked when a screen is being initialized and after the previous widgets have been cleared.
         * Equivalent to Forge's {@code GuiScreenEvent.InitGuiEvent.Pre} event.
         *
         * @param screen The screen.
         * @param access The accessor of the screen.
         * @return A {@link EventResult} determining the outcome of the event,
         * the execution of the vanilla initialization may be cancelled by the result.
         */
        EventResult init(Screen screen, ScreenAccess access);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenInitPost {
        /**
         * Invoked after a screen has been initialized and all the vanilla initialization logic has happened.
         * Equivalent to Forge's {@code GuiScreenEvent.InitGuiEvent.Post} event.
         *
         * @param screen The screen.
         * @param access The accessor of the screen.
         */
        void init(Screen screen, ScreenAccess access);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenRenderPre {
        /**
         * Invoked before any screen is rendered.
         * Equivalent to Forge's {@code GuiScreenEvent.DrawScreenEvent.Pre} event.
         *
         * @param screen   The screen.
         * @param graphics The graphics context.
         * @param mouseX   The scaled x-coordinate of the mouse cursor.
         * @param mouseY   The scaled y-coordinate of the mouse cursor.
         * @param delta    The current tick delta.
         * @return A {@link EventResult} determining the outcome of the event,
         * the vanilla render may be cancelled by the result.
         */
        EventResult render(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ScreenRenderPost {
        /**
         * Invoked after a screen has finished rendering using the vanilla logic.
         * Equivalent to Forge's {@code GuiScreenEvent.DrawScreenEvent.Post} event.
         *
         * @param screen   The screen.
         * @param graphics The graphics context.
         * @param mouseX   The scaled x-coordinate of the mouse cursor.
         * @param mouseY   The scaled y-coordinate of the mouse cursor.
         * @param delta    The current tick delta.
         */
        void render(Screen screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ContainerScreenRenderBackground {
        /**
         * Invoked after a container screen's background are rendered.
         * Equivalent to Forge's {@code ContainerScreenEvent.DrawBackground} event.
         *
         * @param screen   The screen.
         * @param graphics The graphics context.
         * @param mouseX   The scaled x-coordinate of the mouse cursor.
         * @param mouseY   The scaled y-coordinate of the mouse cursor.
         * @param delta    The current tick delta.
         */
        void render(AbstractContainerScreen<?> screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface ContainerScreenRenderForeground {
        /**
         * Invoked after a screen has finished rendering most of the foreground, but before any floating widgets are rendered.
         * Equivalent to Forge's {@code ContainerScreenEvent.DrawForeground} event.
         *
         * @param screen   The screen.
         * @param graphics The graphics context.
         * @param mouseX   The scaled x-coordinate of the mouse cursor.
         * @param mouseY   The scaled y-coordinate of the mouse cursor.
         * @param delta    The current tick delta.
         */
        void render(AbstractContainerScreen<?> screen, GuiGraphics graphics, int mouseX, int mouseY, float delta);
    }

    @Environment(EnvType.CLIENT)
    interface SetScreen {
        /**
         * Invoked before a new screen is set to open.
         * Equivalent to Forge's {@code GuiOpenEvent} event.
         *
         * @param screen The screen that is going to be opened.
         * @return A {@link CompoundEventResult} determining the outcome of the event,
         * if an outcome is set, the vanilla screen is overridden.
         */
        CompoundEventResult<Screen> modifyScreen(Screen screen);
    }
}
