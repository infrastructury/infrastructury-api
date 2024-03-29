package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

public interface LootEvent {
    interface Inner extends remapped.architectury.event.events.common.LootEvent {
    }

    Event<ModifyLootTable> MODIFY_LOOT_TABLE = EventWrapper.of(Inner.MODIFY_LOOT_TABLE, modifyLootTable -> ((lootDataManager, resourceLocation, lootTableModificationContext, b) -> modifyLootTable.modifyLootTable(lootDataManager, resourceLocation, lootTableModificationContext::addPool, b)));

    @FunctionalInterface
    interface ModifyLootTable {
        void modifyLootTable(@Nullable LootDataManager lootDataManager, ResourceLocation id, LootTableModificationContext context, boolean builtin);
    }

    @ApiStatus.NonExtendable
    interface LootTableModificationContext {
        void addPool(LootPool pool);

        default void addPool(LootPool.Builder pool) {
            addPool(pool.build());
        }
    }
}
