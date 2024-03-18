package net.minecraft.client.gui;

#if MC_VER < MC_1_20_1
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;

public class GuiGraphics extends GuiComponent {
    private final PoseStack matrices;

    public GuiGraphics(PoseStack matrices) {
        this.matrices = matrices;
    }

    public int guiWidth() {
        return Minecraft.getInstance().getWindow().getGuiScaledWidth();
    }

    public int guiHeight() {
        return Minecraft.getInstance().getWindow().getGuiScaledHeight();
    }

    public void drawString(Font font, String clockText, int i, int j, int color) {
        GuiComponent.drawString(matrices, font, clockText, i, j, color);
    }

    public void renderItem(ItemStack clockItemStack, int x, int y, int z) {
        #if MC_VER < MC_1_19_4
        Minecraft.getInstance().getItemRenderer().renderGuiItem(clockItemStack, x, y);
        #elif MC_VER < MC_1_20_1
        Minecraft.getInstance().getItemRenderer().renderGuiItem(matrices,clockItemStack,x,y);
        #else
        Minecraft.getInstance().getItemRenderer().renderGuiItem(clockItemStack, x, y);
        #endif
    }
}
#endif
