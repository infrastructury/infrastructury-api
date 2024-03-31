package net.minecraft.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public interface HolderLookup<T> extends HolderGetter<T> {
    Stream<Holder.Reference<T>> listElements();

    default Stream<ResourceKey<T>> listElementIds() {
        return this.listElements().map(Holder.Reference::key);
    }

    Stream<HolderSet.Named<T>> listTags();

    default Stream<TagKey<T>> listTagIds() {
        return this.listTags().map(HolderSet.Named::key);
    }

    default HolderLookup<T> filterElements(final Predicate<T> var1) {
        return new HolderLookup<>() {
            @Override
            public Stream<Holder.Reference<T>> listElements() {
                return Stream.empty();
            }

            @Override
            public Stream<HolderSet.Named<T>> listTags() {
                return Stream.empty();
            }

            @Override
            public Optional<Holder.Reference<T>> get(ResourceKey<T> var1) {
                return Optional.empty();
            }

            @Override
            public Optional<HolderSet.Named<T>> get(TagKey<T> var1) {
                return Optional.empty();
            }
        };
    }
}