package com.mrmelon54.infrastructury.utils;

import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface OptionalSupplier<T> extends Supplier<T> {
    boolean isPresent();

    @Nullable
    default T getOrNull() {
        return isPresent() ? get() : null;
    }

    default Optional<T> toOptional() {
        return Optional.ofNullable(getOrNull());
    }

    default void ifPresent(Consumer<? super T> action) {
        if (isPresent()) action.accept(get());
    }

    default void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (isPresent()) action.accept(get());
        else emptyAction.run();
    }

    default Stream<T> stream() {
        return !isPresent() ? Stream.empty() : Stream.of(get());
    }

    default T orElse(T other) {
        return isPresent() ? get() : other;
    }

    default T orElseGet(Supplier<? extends T> supplier) {
        return isPresent() ? get() : supplier.get();
    }
}
