package com.mrmelon54.OmniPlay.event;

import dev.architectury.event.EventFactory;

import java.util.function.Function;

public class EventWrapper<N, O> implements Event<N> {
    private final Event<O> innerEvent;
    private final Function<N, O> converter;

    private EventWrapper(Event<O> innerEvent, Function<N, O> converter) {
        this.innerEvent = innerEvent;
        this.converter = converter;
    }

    @SafeVarargs
    public static <N, O> Event<N> of(Event<O> innerEvent, Function<N, O> converter, N... typeGetter) {
        Event<N> outer = (Event<N>) EventFactory.createLoop(typeGetter);
        return new EventWrapper<>(innerEvent, converter);
    }

    @Override
    public N invoker() {
        return null;
    }

    @Override
    public void register(N listener) {
        innerEvent.register(converter.apply(listener));
    }

    @Override
    public void unregister(N listener) {

    }

    @Override
    public boolean isRegistered(N listener) {
        return false;
    }

    @Override
    public void clearListeners() {

    }
}
