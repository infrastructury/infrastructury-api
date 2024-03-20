package com.mrmelon54.OmniPlay.event;

#if MC_VER == MC_1_16_5
public interface Event<T> extends me.shedaniel.architectury.event.Event<T> {
#else
public interface Event<T> extends dev.architectury.event.Event<T> {
#endif
}
