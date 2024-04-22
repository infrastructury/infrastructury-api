package com.mrmelon54.infrastructury.registry.level.entity.trade;

import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

public final class TradeRegistry {
    public static void registerVillagerTrade(VillagerProfession profession, int level, VillagerTrades.ItemListing... trades) {
        remapped.architectury.registry.level.entity.trade.TradeRegistry.registerVillagerTrade(profession, level, trades);
    }

    public static void registerTradeForWanderingTrader(boolean rare, VillagerTrades.ItemListing... trades) {
        remapped.architectury.registry.level.entity.trade.TradeRegistry.registerTradeForWanderingTrader(rare, trades);
    }
}
