package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import remapped.architectury.event.Event;

public interface InteractionEvent {
    interface Inner extends remapped.architectury.event.events.common.InteractionEvent {
    }

    Event<LeftClickBlock> LEFT_CLICK_BLOCK = EventWrapper.of(Inner.LEFT_CLICK_BLOCK, leftClickBlock -> ((player, interactionHand, blockPos, direction) -> EventResult.map(leftClickBlock.click(player, interactionHand, blockPos, direction))));
    Event<RightClickBlock> RIGHT_CLICK_BLOCK = EventWrapper.of(Inner.RIGHT_CLICK_BLOCK, rightClickBlock -> ((player, interactionHand, blockPos, direction) -> EventResult.map(rightClickBlock.click(player, interactionHand, blockPos, direction))));
    Event<RightClickItem> RIGHT_CLICK_ITEM = EventWrapper.of(Inner.RIGHT_CLICK_ITEM, rightClickItem -> ((player, interactionHand) -> CompoundEventResult.map(rightClickItem.click(player, interactionHand))));
    Event<ClientLeftClickAir> CLIENT_LEFT_CLICK_AIR = EventWrapper.of(Inner.CLIENT_LEFT_CLICK_AIR, clientLeftClickAir -> clientLeftClickAir::click);
    Event<ClientRightClickAir> CLIENT_RIGHT_CLICK_AIR = EventWrapper.of(Inner.CLIENT_RIGHT_CLICK_AIR, clientRightClickAir -> clientRightClickAir::click);
    Event<InteractEntity> INTERACT_ENTITY = EventWrapper.of(Inner.INTERACT_ENTITY, interactEntity -> ((player, entity, interactionHand) -> EventResult.map(interactEntity.interact(player, entity, interactionHand))));
    Event<FarmlandTrample> FARMLAND_TRAMPLE = EventWrapper.of(Inner.FARMLAND_TRAMPLE, farmlandTrample -> ((level, blockPos, blockState, v, entity) -> EventResult.map(farmlandTrample.trample(level, blockPos, blockState, v, entity))));

    interface RightClickBlock {
        EventResult click(Player player, InteractionHand hand, BlockPos pos, Direction face);
    }

    interface LeftClickBlock {
        EventResult click(Player player, InteractionHand hand, BlockPos pos, Direction face);
    }

    interface RightClickItem {
        CompoundEventResult<ItemStack> click(Player player, InteractionHand hand);
    }

    interface ClientRightClickAir {
        void click(Player player, InteractionHand hand);
    }

    interface ClientLeftClickAir {
        void click(Player player, InteractionHand hand);
    }

    interface InteractEntity {
        EventResult interact(Player player, Entity entity, InteractionHand hand);
    }

    interface FarmlandTrample {
        EventResult trample(Level world, BlockPos pos, BlockState state, float distance, Entity entity);
    }
}
