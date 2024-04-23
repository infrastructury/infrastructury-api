package com.mrmelon54.infrastructury.event.events.common;

// TODO: convert to partial event

#if MC_VER > MC_1_18_2
import com.mojang.brigadier.CommandDispatcher;
import com.mrmelon54.infrastructury.event.Event;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public interface CommandRegistrationEvent {
    interface Inner extends remapped.architectury.event.events.common.CommandRegistrationEvent {

    }

    Event<CommandRegistrationEvent> EVENT = EventWrapper.of(Inner.EVENT, x -> {
        #if MC_VER < MC_1_19_2
        return ((commandDispatcher, commandSelection) -> x.register(commandDispatcher, null, commandSelection));
        #else
        return x::register;
        #endif
    });

    void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection);
}
#endif
