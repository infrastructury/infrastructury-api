package com.mrmelon54.infrastructury.quilt;

import com.mrmelon54.infrastructury.Infrastructury;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class InfrastructuryQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Infrastructury.init();
    }
}
