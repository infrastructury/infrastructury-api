package com.mrmelon54.OmniPlay.event.events.common.fabric;

#if MC_VER > MC_1_16_5 && MC_VER < MC_1_18_2
import com.mrmelon54.OmniPlay.event.events.common.LootEvent;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.world.level.storage.loot.LootDataManager;
import remapped.architectury.event.Event;

public class LootEventImpl {
    static Event<LootEvent.ModifyLootTable> resolveModifyLootTable() {
        return new Event<>() {
            @Override
            public LootEvent.ModifyLootTable invoker() {
                throw new AssertionError("cannot invoker");
            }

            @Override
            public void register(LootEvent.ModifyLootTable modifyLootTable) {
                LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> modifyLootTable.modifyLootTable(new LootDataManager(manager), id, supplier::withPool, false));
            }

            @Override
            public void unregister(LootEvent.ModifyLootTable modifyLootTable) {
                throw new AssertionError("cannot unregister");
            }

            @Override
            public boolean isRegistered(LootEvent.ModifyLootTable modifyLootTable) {
                throw new AssertionError("cannot isRegistered");
            }

            @Override
            public void clearListeners() {
                throw new AssertionError("cannot clearListeners");
            }
        };
    }
}
#endif
