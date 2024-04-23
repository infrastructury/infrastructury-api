package com.mrmelon54.infrastructury.event;

import com.mrmelon54.infrastructury.utils.OptionalSupplier;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class EventWrapper<N, O> implements Event<N> {
    private final remapped.architectury.event.Event<O> innerEvent;
    private final Function<N, O> converter;
    private final Map<N, O> map = new HashMap<>();

    private EventWrapper(remapped.architectury.event.Event<O> innerEvent, Function<N, O> converter) {
        this.innerEvent = innerEvent;
        this.converter = converter;
    }

    private static <N> Event<N> empty(Class<N> clazz) {
        return new Event<>() {
            @Override
            public N invoker() {
                try {
                    return clazz.getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void register(N var1) {
                // Not implemented
            }

            @Override
            public void unregister(N var1) {
                // Not implemented
            }

            @Override
            public boolean isRegistered(N var1) {
                return false;
            }

            @Override
            public void clearListeners() {
                // Not implemented
            }
        };
    }

    public static <N> Event<N> of(remapped.architectury.event.Event<N> innerEvent) {
        return new EventWrapper<>(innerEvent, Function.identity());
    }

    public static <N, O> Event<N> of(remapped.architectury.event.Event<O> innerEvent, Function<N, O> converter) {
        return new EventWrapper<>(innerEvent, converter);
    }

    public static <N> Event<N> select(Supplier<Event<N>> supplier) {
        return supplier.get();
    }

    public static <N> PartialEvent<N> partial(Supplier<Event<N>> supplier) {
        return PartialEvent.of(supplier.get());
    }

    @Override
    public N invoker() {
        throw new RuntimeException("cannot invoke EventWrapper");
    }

    @Override
    public void register(N listener) {
        innerEvent.register(map.computeIfAbsent(listener, converter));
    }

    @Override
    public void unregister(N listener) {
        innerEvent.unregister(map.remove(listener));
    }

    @Override
    public boolean isRegistered(N listener) {
        return innerEvent.isRegistered(map.get(listener));
    }

    @Override
    public void clearListeners() {
        map.values().forEach(innerEvent::unregister);
        map.clear();
    }
}
