package com.mrmelon54.infrastructury.event;

// PartialEvent defines an Event which only operates on some versions.
// eventExists() returns true if the Event functions on the current version.
public interface PartialEvent<T> extends Event<T> {
    static <T> PartialEvent<T> of(Event<T> event) {
        return event == null ? new PartialEvent<>() {
            @Override
            public boolean eventExists() {
                return false;
            }

            @Override
            public T invoker() {
                throw new UnsupportedOperationException("Invoker is not supported for partial events");
            }

            @Override
            public void register(T var1) {
            }

            @Override
            public void unregister(T var1) {
            }

            @Override
            public boolean isRegistered(T var1) {
                return false;
            }

            @Override
            public void clearListeners() {
            }
        } : new PartialEvent<>() {
            @Override
            public boolean eventExists() {
                return true;
            }

            @Override
            public T invoker() {
                return event.invoker();
            }

            @Override
            public void register(T var1) {
                event.register(var1);
            }

            @Override
            public void unregister(T var1) {
                event.unregister(var1);
            }

            @Override
            public boolean isRegistered(T var1) {
                return event.isRegistered(var1);
            }

            @Override
            public void clearListeners() {
                event.clearListeners();
            }
        };
    }

    boolean eventExists();
}
