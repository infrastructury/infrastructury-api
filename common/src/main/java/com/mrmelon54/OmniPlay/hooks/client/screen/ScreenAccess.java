package com.mrmelon54.OmniPlay.hooks.client.screen;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public interface ScreenAccess {
    Screen getScreen();

    List<NarratableEntry> getNarratables();

    List<Renderable> getRenderables();

    <T extends AbstractWidget & Renderable & NarratableEntry> T addRenderableWidget(T widget);

    <T extends Renderable> T addRenderableOnly(T listener);

    <T extends GuiEventListener & NarratableEntry> T addWidget(T listener);
}
