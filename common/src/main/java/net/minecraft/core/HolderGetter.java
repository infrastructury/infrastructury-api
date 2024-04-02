package net.minecraft.core;

#if MC_VER < MC_1_20_1
import java.util.Optional;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

public interface HolderGetter<T> {
    Optional<Holder.Reference<T>> get(ResourceKey<T> var1);

    default Holder.Reference<T> getOrThrow(ResourceKey<T> var1) {
        return (Holder.Reference<T>) this.get(var1).orElseThrow(() -> new IllegalStateException("Missing element " + var1));
    }

    Optional<HolderSet.Named<T>> get(TagKey<T> var1);

    default HolderSet.Named<T> getOrThrow(TagKey<T> var1) {
        return (HolderSet.Named<T>) this.get(var1).orElseThrow(() -> new IllegalStateException("Missing tag " + var1));
    }
}
#endif
