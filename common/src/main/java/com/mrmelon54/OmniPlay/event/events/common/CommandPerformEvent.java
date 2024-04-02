package com.mrmelon54.OmniPlay.event.events.common;

import com.mojang.brigadier.ParseResults;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;
import remapped.architectury.event.EventActor;

public class CommandPerformEvent {
    static class Inner extends remapped.architectury.event.events.common.CommandPerformEvent {
        public Inner(ParseResults<CommandSourceStack> results, @Nullable Throwable throwable) {
            super(results, throwable);
        }
    }

    public static final Event<EventActor<CommandPerformEvent>> EVENT = EventWrapper.of(Inner.EVENT, commandPerformEventEventActor -> commandPerformEvent -> commandPerformEventEventActor.act(new CommandPerformEvent(commandPerformEvent)));

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
