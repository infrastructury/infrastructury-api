package com.mrmelon54.infrastructury.event.events.common;

import com.mrmelon54.infrastructury.event.EventResult;
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;
import remapped.architectury.utils.value.IntValue;

public interface BlockEvent {
    interface Inner extends remapped.architectury.event.events.common.BlockEvent {
    }

    Event<Break> BREAK = EventWrapper.of(Inner.BREAK, aBreak -> (level, blockPos, blockState, serverPlayer, intValue) -> EventResult.map2(aBreak.breakBlock(level, blockPos, blockState, serverPlayer, intValue)));
    Event<Place> PLACE = EventWrapper.of(Inner.PLACE, place -> (level, blockPos, blockState, entity) -> EventResult.map2(place.placeBlock(level, blockPos, blockState, entity)));
    Event<FallingLand> FALLING_LAND = EventWrapper.of(Inner.FALLING_LAND, fallingLand -> fallingLand::onLand);

    interface Break {
        EventResult breakBlock(Level level, BlockPos pos, BlockState state, ServerPlayer player, @Nullable IntValue xp);
    }

    interface Place {
        EventResult placeBlock(Level level, BlockPos pos, BlockState state, @Nullable Entity placer);
    }

    interface FallingLand {
        void onLand(Level level, BlockPos pos, BlockState fallState, BlockState landOn, FallingBlockEntity entity);
    }
}
