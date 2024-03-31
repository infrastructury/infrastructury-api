package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.CompoundEventResult;
import com.mrmelon54.OmniPlay.event.EventResult;
import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

public interface PlayerEvent {
    private static remapped.architectury.event.events.common.PlayerEvent.PlayerAdvancement mapPlayerAdvancement(PlayerAdvancement playerAdvancement) {
        #if MC_VER < MC_1_20_2
        return ((serverPlayer, advancement) -> playerAdvancement.award(serverPlayer, new AdvancementHolder(advancement.getId(), advancement)));
        #else
        return playerAdvancement::award;
        #endif
    }

    interface Inner extends remapped.architectury.event.events.common.PlayerEvent {
    }

    Event<PlayerJoin> PLAYER_JOIN = EventWrapper.of(Inner.PLAYER_JOIN, playerJoin -> playerJoin::join);
    Event<PlayerQuit> PLAYER_QUIT = EventWrapper.of(Inner.PLAYER_QUIT, playerQuit -> playerQuit::quit);
    Event<PlayerRespawn> PLAYER_RESPAWN = EventWrapper.of(Inner.PLAYER_RESPAWN, playerRespawn -> playerRespawn::respawn);
    Event<PlayerAdvancement> PLAYER_ADVANCEMENT = EventWrapper.of(Inner.PLAYER_ADVANCEMENT, PlayerEvent::mapPlayerAdvancement);
    Event<PlayerClone> PLAYER_CLONE = EventWrapper.of(Inner.PLAYER_CLONE, playerClone -> playerClone::clone);
    Event<CraftItem> CRAFT_ITEM = EventWrapper.of(Inner.CRAFT_ITEM, craftItem -> craftItem::craft);
    Event<SmeltItem> SMELT_ITEM = EventWrapper.of(Inner.SMELT_ITEM, smeltItem -> smeltItem::smelt);
    Event<PickupItemPredicate> PICKUP_ITEM_PRE = EventWrapper.of(Inner.PICKUP_ITEM_PRE, pickupItemPredicate -> ((player, itemEntity, itemStack) -> EventResult.map(pickupItemPredicate.canPickup(player, itemEntity, itemStack))));
    Event<PickupItem> PICKUP_ITEM_POST = EventWrapper.of(Inner.PICKUP_ITEM_POST, pickupItem -> pickupItem::pickup);
    Event<ChangeDimension> CHANGE_DIMENSION = EventWrapper.of(Inner.CHANGE_DIMENSION, changeDimension -> changeDimension::change);
    Event<DropItem> DROP_ITEM = EventWrapper.of(Inner.DROP_ITEM, dropItem -> ((player, itemEntity) -> EventResult.map(dropItem.drop(player, itemEntity))));
    Event<OpenMenu> OPEN_MENU = EventWrapper.of(Inner.OPEN_MENU, openMenu -> openMenu::open);
    Event<CloseMenu> CLOSE_MENU = EventWrapper.of(Inner.CLOSE_MENU, closeMenu -> closeMenu::close);
    Event<FillBucket> FILL_BUCKET = EventWrapper.of(Inner.FILL_BUCKET, fillBucket -> ((player, level, itemStack, hitResult) -> CompoundEventResult.map(fillBucket.fill(player, level, itemStack, hitResult))));
    Event<AttackEntity> ATTACK_ENTITY = EventWrapper.of(Inner.ATTACK_ENTITY, attackEntity -> ((player, level, entity, interactionHand, entityHitResult) -> EventResult.map(attackEntity.attack(player, level, entity, interactionHand, entityHitResult))));

    interface PlayerJoin {
        void join(ServerPlayer player);
    }

    interface PlayerQuit {
        void quit(ServerPlayer player);
    }

    interface PlayerRespawn {
        void respawn(ServerPlayer newPlayer, boolean conqueredEnd);
    }

    interface PlayerClone {
        void clone(ServerPlayer oldPlayer, ServerPlayer newPlayer, boolean wonGame);
    }

    interface PlayerAdvancement {
        void award(ServerPlayer player, AdvancementHolder advancement);
    }

    interface CraftItem {
        void craft(Player player, ItemStack constructed, Container inventory);
    }

    interface SmeltItem {
        void smelt(Player player, ItemStack smelted);
    }

    interface PickupItemPredicate {
        EventResult canPickup(Player player, ItemEntity entity, ItemStack stack);
    }

    interface PickupItem {
        void pickup(Player player, ItemEntity entity, ItemStack stack);
    }

    interface ChangeDimension {
        void change(ServerPlayer player, ResourceKey<Level> oldLevel, ResourceKey<Level> newLevel);
    }

    interface DropItem {
        EventResult drop(Player player, ItemEntity entity);
    }

    interface OpenMenu {
        void open(Player player, AbstractContainerMenu menu);
    }

    interface CloseMenu {
        void close(Player player, AbstractContainerMenu menu);
    }

    interface FillBucket {
        CompoundEventResult<ItemStack> fill(Player player, Level level, ItemStack stack, @Nullable HitResult target);
    }

    interface AttackEntity {
        EventResult attack(Player player, Level level, Entity target, InteractionHand hand, @Nullable EntityHitResult result);
    }
}
