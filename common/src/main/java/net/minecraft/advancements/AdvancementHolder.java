package net.minecraft.advancements;

#if MC_VER < MC_1_20_2
import net.minecraft.resources.ResourceLocation;

#if MC_VER == MC_1_16_5
public class AdvancementHolder {
    ResourceLocation id;
    Advancement value;

    public AdvancementHolder(ResourceLocation id, Advancement value) {
        this.id=id;
        this.value=value;
    }
#else
public record AdvancementHolder(ResourceLocation id, Advancement value) {
#endif
}
#endif
