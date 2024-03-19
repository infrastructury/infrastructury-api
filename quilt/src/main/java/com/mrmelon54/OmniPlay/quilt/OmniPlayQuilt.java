package com.mrmelon54.OmniPlay.quilt;

import com.mrmelon54.OmniPlay.OmniPlay;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class OmniPlayQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        OmniPlay.init();
    }
}
