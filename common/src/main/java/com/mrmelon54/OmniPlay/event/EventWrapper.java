package com.mrmelon54.OmniPlay.event;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.event.Event;
#else
import dev.architectury.event.Event;
#endif

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class EventWrapper<N> implements Event<N> {
    private final List<N> listeners = new ArrayList<>();

    private EventWrapper() {
    }

    public static <N, O> EventWrapper<N> create(Event<O> innerEvent, Function<List<N>, O> converter) {
        EventWrapper<N> wrapper = new EventWrapper<>();
        innerEvent.register(converter.apply(wrapper.listeners));
        return wrapper;
    }

    @Override
    public N invoker() {
        throw new RuntimeException("invoker should not be called here");
    }

    @Override
    public void register(N listener) {
        listeners.add(listener);
    }

    @Override
    public void unregister(N listener) {
        listeners.remove(listener);
    }

    @Override
    public boolean isRegistered(N listener) {
        return listeners.contains(listener);
    }

    @Override
    public void clearListeners() {
        listeners.clear();
    }
}
