package com.mrmelon54.infrastructury.event;

import remapped.architectury.event.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EventWrapper<N, O> implements Event<N> {
    private final Event<O> innerEvent;
    private final Function<N, O> converter;
    private final Map<N, O> map = new HashMap<>();

    private EventWrapper(Event<O> innerEvent, Function<N, O> converter) {
        this.innerEvent = innerEvent;
        this.converter = converter;
    }

    public static <N, O> Event<N> of(Event<O> innerEvent, Function<N, O> converter) {
        return new EventWrapper<>(innerEvent, converter);
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
