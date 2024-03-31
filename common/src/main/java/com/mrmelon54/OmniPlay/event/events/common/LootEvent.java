package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTables;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

public interface LootEvent {
    interface Inner extends remapped.architectury.event.events.common.LootEvent {
    }

    private static remapped.architectury.event.events.common.LootEvent.ModifyLootTable mapModifyLootTable(ModifyLootTable modifyLootTable) {
        #if MC_VER >= MC_1_18_2 && MC_VER < MC_1_20_1
        return ((lootDataManager, resourceLocation, lootTableModificationContext, b) -> modifyLootTable.modifyLootTable(new LootDataManager(lootDataManager), resourceLocation, lootTableModificationContext::addPool, b));
        #else
        return ((lootDataManager, resourceLocation, lootTableModificationContext, b) -> modifyLootTable.modifyLootTable(lootDataManager, resourceLocation, lootTableModificationContext::addPool, b));
        #endif
    }

    Event<ModifyLootTable> MODIFY_LOOT_TABLE = resolveModifyLootTable();

    @ExpectPlatform
    static Event<ModifyLootTable> resolveModifyLootTable() {
        return EventWrapper.of(Inner.MODIFY_LOOT_TABLE, LootEvent::mapModifyLootTable);
    }

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
