package com.mrmelon54.OmniPlay.quilt;

import com.mrmelon54.OmniPlay.fabriclike.OmniPlayFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class OmniPlayQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        OmniPlayFabricLike.init();
    }
}
