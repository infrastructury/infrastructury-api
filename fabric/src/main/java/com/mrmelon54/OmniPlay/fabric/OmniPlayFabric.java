package com.mrmelon54.OmniPlay.fabric;

import com.mrmelon54.OmniPlay.OmniPlay;
import net.fabricmc.api.ModInitializer;

public class OmniPlayFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        OmniPlay.init();
    }
}
