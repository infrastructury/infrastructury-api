package net.minecraft.world.level.storage.loot;

#if MC_VER < MC_1_20_1
import java.util.Optional;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

@FunctionalInterface
public interface LootDataResolver {
    @Nullable
    <T> T getElement(LootDataId<T> var1);

    @Nullable
    default <T> T getElement(LootDataType<T> var1, ResourceLocation var2) {
        return this.getElement(new LootDataId<>(var1, var2));
    }

    default <T> Optional<T> getElementOptional(LootDataId<T> var1) {
        return Optional.ofNullable(this.getElement(var1));
    }

    default <T> Optional<T> getElementOptional(LootDataType<T> var1, ResourceLocation var2) {
        return this.getElementOptional(new LootDataId<>(var1, var2));
    }
}
#endif
