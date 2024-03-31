package com.mrmelon54.OmniPlay.fabric.event.events.common;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import com.mrmelon54.OmniPlay.event.events.common.LootEvent;
import remapped.architectury.event.Event;

public class LootEventImpl {
    static Event<LootEvent.ModifyLootTable> resolveModifyLootTable() {
        return EventWrapper.of(LootEvent.Inner.MODIFY_LOOT_TABLE, LootEvent::mapModifyLootTable);
    }
}
