package com.mrmelon54.infrastructury.util;

import net.minecraft.client.gui.GuiGraphics;

#if MC_VER < MC_1_20_1
import com.mojang.blaze3d.vertex.PoseStack;
#endif

public class Graphics {
    #if MC_VER < MC_1_20_1
    public static GuiGraphics get(PoseStack matrices) {
        return new GuiGraphics(matrices);
    }
    #else
    public static GuiGraphics get(GuiGraphics guiGraphics) {
        return guiGraphics;
    }
    #endif
}
