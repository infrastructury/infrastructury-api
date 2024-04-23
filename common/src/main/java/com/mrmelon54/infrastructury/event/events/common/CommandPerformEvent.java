package com.mrmelon54.infrastructury.event.events.common;

import com.mojang.brigadier.ParseResults;
import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.EventActor;

#if MC_VER == MC_1_16_5
import remapped.architectury.event.Actor;
#endif

public class CommandPerformEvent {
    static class Inner extends remapped.architectury.event.events.common.CommandPerformEvent {
        public Inner(ParseResults<CommandSourceStack> results, @Nullable Throwable throwable) {
            super(results, throwable);
        }
    }

    #if MC_VER == MC_1_16_5
    private static Actor<remapped.architectury.event.events.common.CommandPerformEvent> mapCommandPerformEvent(EventActor<CommandPerformEvent> commandPerformEventEventActor) {
        return commandPerformEvent -> EventResult.map2(commandPerformEventEventActor.act(new CommandPerformEvent(commandPerformEvent)));
    }
    #else
    private static EventActor<remapped.architectury.event.events.common.CommandPerformEvent> mapCommandPerformEvent(EventActor<CommandPerformEvent> commandPerformEventEventActor) {
        return commandPerformEvent -> EventResult.map2(commandPerformEventEventActor.act(new CommandPerformEvent(commandPerformEvent)));
    }
    #endif

    public static final Event<EventActor<CommandPerformEvent>> EVENT = EventWrapper.of(Inner.EVENT, CommandPerformEvent::mapCommandPerformEvent);

    private ParseResults<CommandSourceStack> results;
    @Nullable
    private Throwable throwable;

    public CommandPerformEvent(ParseResults<CommandSourceStack> results, @Nullable Throwable throwable) {
        this.results = results;
        this.throwable = throwable;
    }

    public CommandPerformEvent(remapped.architectury.event.events.common.CommandPerformEvent commandPerformEvent) {
        this.results = commandPerformEvent.getResults();
        this.throwable = commandPerformEvent.getThrowable();
    }

    public ParseResults<CommandSourceStack> getResults() {
        return results;
    }

    public void setResults(ParseResults<CommandSourceStack> results) {
        this.results = results;
    }

    @Nullable
    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(@Nullable Throwable throwable) {
        this.throwable = throwable;
    }
}
