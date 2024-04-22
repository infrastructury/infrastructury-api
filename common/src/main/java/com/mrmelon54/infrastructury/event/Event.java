package com.mrmelon54.infrastructury.event;

public interface Event<T> {
    T invoker();

    void register(T var1);

    void unregister(T var1);

    boolean isRegistered(T var1);

    void clearListeners();
}
