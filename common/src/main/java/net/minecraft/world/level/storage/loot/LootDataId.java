package net.minecraft.world.level.storage.loot;

#if MC_VER == MC_1_16_5

import net.minecraft.resources.ResourceLocation;

public class LootDataId<T> {
    public LootDataId(LootDataType<T> type, ResourceLocation location) {
        this.type = type;
        this.location = location;
    }

    LootDataType<T> type;
    ResourceLocation location;
}
#elif MC_VER < MC_1_20_1
import net.minecraft.resources.ResourceLocation;

public record LootDataId<T>(LootDataType<T> type, ResourceLocation location) {
}
#endif
