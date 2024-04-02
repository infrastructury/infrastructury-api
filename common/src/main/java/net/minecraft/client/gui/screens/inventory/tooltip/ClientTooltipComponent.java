package net.minecraft.client.gui.screens.inventory.tooltip;

#if MC_VER == MC_1_16_5
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Matrix4f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureManager;

@Environment(EnvType.CLIENT)
public interface ClientTooltipComponent {
    int getHeight();

    int getWidth(Font font);

    default void renderText(Font font, int x, int y, Matrix4f matrix4f, MultiBufferSource.BufferSource bufferSource) {
    }

    default void renderImage(Font font, int mouseX, int mouseY, PoseStack poseStack, ItemRenderer itemRenderer, int blitOffset, TextureManager textureManager) {
    }
}
#endif
