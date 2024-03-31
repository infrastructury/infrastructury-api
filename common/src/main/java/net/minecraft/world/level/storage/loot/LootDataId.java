package net.minecraft.world.level.storage.loot;

#if MC_VER < MC_1_20_1
import net.minecraft.resources.ResourceLocation;

public record LootDataId<T>(LootDataType<T> type, ResourceLocation location) {
}
#endif
