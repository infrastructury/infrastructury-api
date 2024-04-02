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

import java.util.List;

#if MC_VER > MC_1_17_1
import remapped.architectury.impl.TooltipAdditionalContextsImpl;
#endif

#if MC_VER <= MC_1_17_1
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;

import java.util.function.Function;
import java.util.stream.Collectors;
#endif

@Environment(EnvType.CLIENT)
public interface ClientTooltipEvent {
    interface Inner extends remapped.architectury.event.events.client.ClientTooltipEvent {
    }

    static Event<Render> resolveRenderPre() {
        #if MC_VER <= MC_1_17_1
        return new Event<Render>() {
            @Override
            public Render invoker() {
                throw new RuntimeException("cannot invoke EventWrapper");
            }

            @Override
            public void register(Render render) {
                Inner.RENDER_VANILLA_PRE.register((poseStack, list, i, i1) -> EventResult.map2(render.renderTooltip(Graphics.get(poseStack), list.stream().map((Function<FormattedCharSequence, ClientTooltipComponent>) formattedCharSequence -> new ClientTooltipComponent() {
                    @Override
                    public int getHeight() {
                        return 10;
                    }

                    @Override
                    public int getWidth(Font font) {
                        return font.width(formattedCharSequence);
                    }
                }).collect(Collectors.toList()), i, i1)));
                Inner.RENDER_FORGE_PRE.register((poseStack, list, i, i1) -> EventResult.map2(render.renderTooltip(Graphics.get(poseStack), list.stream().map((Function<FormattedText, ClientTooltipComponent>) formattedText -> new ClientTooltipComponent() {
                    @Override
                    public int getHeight() {
                        return 10;
                    }

                    @Override
                    public int getWidth(Font font) {
                        return font.width(formattedText);
                    }
                }).collect(Collectors.toList()), i, i1)));
            }

            @Override
            public void unregister(Render render) {
                throw new RuntimeException("cannot invoke unregister");
            }

            @Override
            public boolean isRegistered(Render render) {
                throw new RuntimeException("cannot invoke isRegistered");
            }

            @Override
            public void clearListeners() {

            }
        };
        #else
        return EventWrapper.of(Inner.RENDER_PRE, render -> ((guiGraphics, list, i, i1) -> EventResult.map(render.renderTooltip(Graphics.get(guiGraphics), list, i, i1))));
        #endif
    }

    Event<Item> ITEM = EventWrapper.of(Inner.ITEM, item -> item::append);
    Event<Render> RENDER_PRE = resolveRenderPre();

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
        #if MC_VER <= MC_1_17_1
        // TODO: support additional contexts in 1.17.1 and below
        return new AdditionalContexts() {
            @Override
            public @Nullable ItemStack getItem() {
                return null;
            }

            @Override
            public void setItem(@Nullable ItemStack stack) {
            }
        };
        #else
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
        #endif
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
