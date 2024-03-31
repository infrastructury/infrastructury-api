package com.mrmelon54.OmniPlay.event.events.client;

import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import com.mrmelon54.OmniPlay.util.Graphics;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;
import remapped.architectury.impl.TooltipAdditionalContextsImpl;

import java.util.List;

@Environment(EnvType.CLIENT)
public interface ClientTooltipEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientTooltipEvent {
    }

    Event<Item> ITEM = EventWrapper.of(Inner.ITEM, item -> item::append);
    Event<Render> RENDER_PRE = EventWrapper.of(Inner.RENDER_PRE, render -> ((guiGraphics, list, i, i1) -> EventResult.map(render.renderTooltip(Graphics.get(guiGraphics), list, i, i1))));
    Event<RenderModifyPosition> RENDER_MODIFY_POSITION = EventWrapper.of(Inner.RENDER_MODIFY_POSITION, renderModifyPosition -> ((guiGraphics, positionContext) -> renderModifyPosition.renderTooltip(Graphics.get(guiGraphics), new PositionContext() {
        @Override
        public int getTooltipX() {
            return positionContext.getTooltipX();
        }

        @Override
        public void setTooltipX(int x) {
            positionContext.setTooltipX(x);
        }

        @Override
        public int getTooltipY() {
            return positionContext.getTooltipY();
        }

        @Override
        public void setTooltipY(int y) {
            positionContext.setTooltipY(y);
        }
    })));
    Event<RenderModifyColor> RENDER_MODIFY_COLOR = EventWrapper.of(Inner.RENDER_MODIFY_COLOR, renderModifyColor -> ((guiGraphics, i, i1, colorContext) -> renderModifyColor.renderTooltip(Graphics.get(guiGraphics), i, i1, new ColorContext() {
        @Override
        public int getBackgroundColor() {
            return colorContext.getBackgroundColor();
        }

        @Override
        public void setBackgroundColor(int color) {
            colorContext.setBackgroundColor(color);
        }

        @Override
        public int getOutlineGradientTopColor() {
            return colorContext.getOutlineGradientTopColor();
        }

        @Override
        public void setOutlineGradientTopColor(int color) {
            colorContext.setOutlineGradientTopColor(color);
        }

        @Override
        public int getOutlineGradientBottomColor() {
            return colorContext.getOutlineGradientBottomColor();
        }

        @Override
        public void setOutlineGradientBottomColor(int color) {
            colorContext.setOutlineGradientBottomColor(color);
        }
    })));

    static AdditionalContexts additionalContexts() {
        remapped.architectury.event.events.client.ClientTooltipEvent.AdditionalContexts additionalContexts = TooltipAdditionalContextsImpl.get();
        return new AdditionalContexts() {
            @Override
            public @Nullable ItemStack getItem() {
                return additionalContexts.getItem();
            }

            @Override
            public void setItem(@Nullable ItemStack stack) {
                additionalContexts.setItem(stack);
            }
        };
    }

    @ApiStatus.NonExtendable
    interface AdditionalContexts {
        @Nullable
        ItemStack getItem();

        void setItem(@Nullable ItemStack stack);
    }

    @Environment(EnvType.CLIENT)
    interface Item {
        void append(ItemStack stack, List<Component> lines, TooltipFlag flag);
    }

    @Environment(EnvType.CLIENT)
    interface Render {
        EventResult renderTooltip(GuiGraphics graphics, List<? extends ClientTooltipComponent> texts, int x, int y);
    }

    @Environment(EnvType.CLIENT)
    interface RenderModifyPosition {
        void renderTooltip(GuiGraphics graphics, PositionContext context);
    }

    @Environment(EnvType.CLIENT)
    interface RenderModifyColor {
        void renderTooltip(GuiGraphics graphics, int x, int y, ColorContext context);
    }

    @Environment(EnvType.CLIENT)
    interface PositionContext {
        int getTooltipX();

        void setTooltipX(int x);

        int getTooltipY();

        void setTooltipY(int y);
    }

    @Environment(EnvType.CLIENT)
    interface ColorContext {
        int getBackgroundColor();

        void setBackgroundColor(int color);

        int getOutlineGradientTopColor();

        void setOutlineGradientTopColor(int color);

        int getOutlineGradientBottomColor();

        void setOutlineGradientBottomColor(int color);
    }
}
