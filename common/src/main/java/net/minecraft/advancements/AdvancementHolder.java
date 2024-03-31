package net.minecraft.advancements;

#if MC_VER < MC_1_20_2
import net.minecraft.resources.ResourceLocation;

public record AdvancementHolder(ResourceLocation id, Advancement value) {
}
#endif
