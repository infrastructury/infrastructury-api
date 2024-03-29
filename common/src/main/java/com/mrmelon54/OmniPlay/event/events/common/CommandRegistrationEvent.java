package com.mrmelon54.OmniPlay.event.events.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import remapped.architectury.event.Event;

public interface CommandRegistrationEvent {
    interface Inner extends remapped.architectury.event.events.common.CommandRegistrationEvent {
    }

    Event<CommandRegistrationEvent> EVENT = EventWrapper.of(Inner.EVENT, commandRegistrationEvent -> commandRegistrationEvent::register);

    void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registry, Commands.CommandSelection selection);
}
