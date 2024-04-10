package com.mrmelon54.infrastructury.fabric;

import com.mrmelon54.infrastructury.Infrastructury;
import net.fabricmc.api.ModInitializer;

public class InfrastructuryFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Infrastructury.init();
    }
}
