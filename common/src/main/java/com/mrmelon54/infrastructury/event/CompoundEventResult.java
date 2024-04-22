package com.mrmelon54.infrastructury.event;

import net.minecraft.world.InteractionResultHolder;

public class CompoundEventResult<T> {
    private final EventResult result;
    private final T object;

    #if MC_VER == MC_1_16_5
    public static <T> InteractionResultHolder<T> map2(CompoundEventResult<T> result) {
        return map(result).asMinecraft();
    }
    #else
    public static <T> remapped.architectury.event.CompoundEventResult<T> map2(CompoundEventResult<T> result) {
        return map(result);
    }
    #endif

    public static <T> remapped.architectury.event.CompoundEventResult<T> map(CompoundEventResult<T> result) {
        if (result.result == EventResult.pass()) return remapped.architectury.event.CompoundEventResult.pass();
        return remapped.architectury.event.CompoundEventResult.interrupt(result.result.value(), result.object);
    }

    public static <T> CompoundEventResult<T> fromEventResult(EventResult result) {
        return CompoundEventResult.interrupt(result.value(), null);
    }

    public static <T> CompoundEventResult<T> pass() {
        return new CompoundEventResult<>(EventResult.pass(), null);
    }

    public static <T> CompoundEventResult<T> interrupt(Boolean value, T object) {
        return new CompoundEventResult<>(EventResult.interrupt(value), object);
    }

    public static <T> CompoundEventResult<T> interruptTrue(T object) {
        return new CompoundEventResult<>(EventResult.interruptTrue(), object);
    }

    public static <T> CompoundEventResult<T> interruptDefault(T object) {
        return new CompoundEventResult<>(EventResult.interruptDefault(), object);
    }

    public static <T> CompoundEventResult<T> interruptFalse(T object) {
        return new CompoundEventResult<>(EventResult.interruptFalse(), object);
    }

    private CompoundEventResult(EventResult result, T object) {
        this.result = result;
        this.object = object;
    }

    public boolean interruptsFurtherEvaluation() {
        return result.interruptsFurtherEvaluation();
    }

    public Boolean value() {
        return result.value();
    }

    public boolean isEmpty() {
        return result.isEmpty();
    }

    public boolean isPresent() {
        return result.isPresent();
    }

    public boolean isTrue() {
        return result.isTrue();
    }

    public boolean isFalse() {
        return result.isFalse();
    }

    public EventResult result() {
        return result;
    }

    public T object() {
        return object;
    }

    public InteractionResultHolder<T> asMinecraft() {
        return new InteractionResultHolder<>(result.asMinecraft(), object);
    }
}
