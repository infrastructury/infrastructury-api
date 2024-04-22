package com.mrmelon54.infrastructury.utils;

import net.fabricmc.api.EnvType;

public enum Env {
    CLIENT,
    SERVER;

    public static Env fromPlatform(Object type) {
        return type == EnvType.CLIENT ? CLIENT : (type == EnvType.SERVER ? SERVER : null);
    }

    public EnvType toPlatform() {
        return this == CLIENT ? EnvType.CLIENT : EnvType.SERVER;
    }

    public static Env convert(remapped.architectury.utils.Env env) {
        return env == remapped.architectury.utils.Env.CLIENT ? CLIENT : SERVER;
    }
}
