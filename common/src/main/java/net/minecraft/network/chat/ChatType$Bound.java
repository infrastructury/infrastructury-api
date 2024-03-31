package net.minecraft.network.chat;

import org.jetbrains.annotations.Nullable;

public record ChatType$Bound(ChatType chatType, Component name, @Nullable Component targetName) {
    #if MC_VER < MC_1_19_2
    public ChatType$Bound(ChatType bound) {
        this(bound, Component.nullToEmpty(bound.name()), null);
    }
    #else
    public ChatType$Bound(ChatType.Bound bound) {
        this(bound.chatType(), bound.name(), bound.targetName());
    }
    #endif
}
