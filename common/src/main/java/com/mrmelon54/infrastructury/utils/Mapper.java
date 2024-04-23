package com.mrmelon54.infrastructury.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.function.Function;

public final class Mapper {
    public static <N, O> Iterable<N> map(Iterable<O> iterable, Function<O, N> mapper) {
        return new Iterable<>() {
            @NotNull
            @Override
            public Iterator<N> iterator() {
                return map(iterable.iterator(), mapper);
            }
        };
    }

    public static <N, O> Iterator<N> map(Iterator<O> iterator, Function<O, N> mapper) {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public N next() {
                return mapper.apply(iterator.next());
            }
        };
    }
}
