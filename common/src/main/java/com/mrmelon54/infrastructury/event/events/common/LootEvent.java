package com.mrmelon54.infrastructury.event.events.common;

#if MC_VER != MC_1_16_5
import com.mrmelon54.infrastructury.event.EventWrapper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootPool;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

#if MC_VER < MC_1_20_1
import net.minecraft.world.level.storage.loot.LootTables;
#endif

#if MC_VER < MC_1_18_2
import dev.architectury.injectables.annotations.ExpectPlatform;
#endif

public interface LootEvent {
    #if MC_VER >= MC_1_18_2
    interface Inner extends remapped.architectury.event.events.common.LootEvent {
    }

    private static remapped.architectury.event.events.common.LootEvent.ModifyLootTable mapModifyLootTable(ModifyLootTable modifyLootTable) {
        #if MC_VER < MC_1_18_2
        return null;
        #elif MC_VER < MC_1_20_1
        return ((lootDataManager, resourceLocation, lootTableModificationContext, b) -> modifyLootTable.modifyLootTable(new LootDataManager(lootDataManager), resourceLocation, lootTableModificationContext::addPool, b));
        #else
        return ((lootDataManager, resourceLocation, lootTableModificationContext, b) -> modifyLootTable.modifyLootTable(lootDataManager, resourceLocation, lootTableModificationContext::addPool, b));
        #endif
    }
    #endif

    Event<ModifyLootTable> MODIFY_LOOT_TABLE = resolveModifyLootTable();

    #if MC_VER < MC_1_18_2
    @ExpectPlatform
    #endif
    static Event<ModifyLootTable> resolveModifyLootTable() {
        #if MC_VER < MC_1_18_2
        throw new AssertionError("missing resolveModifyLootTable()");
        #else
        return EventWrapper.of(Inner.MODIFY_LOOT_TABLE, LootEvent::mapModifyLootTable);
        #endif
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
#endif
