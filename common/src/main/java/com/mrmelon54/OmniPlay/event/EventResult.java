package com.mrmelon54.OmniPlay.event;

import net.minecraft.world.InteractionResult;
import org.apache.commons.lang3.BooleanUtils;

public final class EventResult {
    private static final EventResult TRUE = new EventResult(true, true);
    private static final EventResult STOP = new EventResult(true, null);
    private static final EventResult PASS = new EventResult(false, null);
    private static final EventResult FALSE = new EventResult(true, false);

    #if MC_VER == MC_1_16_5
    public static InteractionResult map(EventResult result) {
        return result.asMinecraft();
    }

    public static InteractionResult map(remapped.architectury.event.EventResult result) {
        return map(EventResult.interrupt(result.value()));
    }
    #else
    public static remapped.architectury.event.EventResult map(EventResult result) {
        return remapped.architectury.event.EventResult.interrupt(result.interruptsFurtherEvaluation);
    }
    #endif

    public static EventResult pass() {
        return PASS;
    }

    public static EventResult interrupt(Boolean value) {
        if (value == null) return STOP;
        if (value) return TRUE;
        return FALSE;
    }

    public static EventResult interruptTrue() {
        return TRUE;
    }

    public static EventResult interruptDefault() {
        return STOP;
    }

    public static EventResult interruptFalse() {
        return FALSE;
    }

    private final boolean interruptsFurtherEvaluation;

    private final Boolean value;

    EventResult(boolean interruptsFurtherEvaluation, Boolean value) {
        this.interruptsFurtherEvaluation = interruptsFurtherEvaluation;
        this.value = value;
    }

    public boolean interruptsFurtherEvaluation() {
        return interruptsFurtherEvaluation;
    }

    public Boolean value() {
        return value;
    }

    public boolean isEmpty() {
        return value == null;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isTrue() {
        return BooleanUtils.isTrue(value);
    }

    public boolean isFalse() {
        return BooleanUtils.isFalse(value);
    }

    public InteractionResult asMinecraft() {
        if (isPresent()) {
            return value() ? InteractionResult.SUCCESS : InteractionResult.FAIL;
        }
        return InteractionResult.PASS;
    }
}
