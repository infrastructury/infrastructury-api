package com.mrmelon54.OmniPlay.event.events.common.forge;

#if MC_VER < MC_1_18_2
import com.mrmelon54.OmniPlay.event.events.common.LootEvent;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import remapped.architectury.event.Event;
import remapped.architectury.event.EventFactory;

@Mod.EventBusSubscriber
public class LootEventImpl {
    private static Event<LootEvent.ModifyLootTable> EVENT = EventFactory.createLoop(LootEvent.ModifyLootTable.class);

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void register(LootTableLoadEvent event) {
        EVENT.invoker().modifyLootTable(new LootDataManager(event.getLootTableManager()), event.getName(), pool -> event.getTable().addPool(pool), true);
    }

    static Event<LootEvent.ModifyLootTable> resolveModifyLootTable() {
        return EVENT;
    }
}
#endif
