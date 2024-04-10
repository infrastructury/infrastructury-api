package com.mrmelon54.infrastructury.event.events.common;

#if MC_VER > MC_1_18_2
import com.mojang.brigadier.CommandDispatcher;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import remapped.architectury.event.Event;

public interface CommandRegistrationEvent {
    interface Inner extends remapped.architectury.event.events.common.CommandRegistrationEvent {

    }

    static remapped.architectury.event.events.common.CommandRegistrationEvent mapCommandRegistrationEvent(CommandRegistrationEvent commandRegistrationEvent) {
        #if MC_VER < MC_1_19_2
        return ((commandDispatcher, commandSelection) -> commandRegistrationEvent.register(commandDispatcher, null, commandSelection));
        #else
        return commandRegistrationEvent::register;
        #endif
    }

    Event<CommandRegistrationEvent> EVENT = EventWrapper.of(Inner.EVENT, CommandRegistrationEvent::mapCommandRegistrationEvent);

    void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection);
}
#endif
