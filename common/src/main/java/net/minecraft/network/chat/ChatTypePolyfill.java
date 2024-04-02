package net.minecraft.network.chat;

import org.jetbrains.annotations.Nullable;

#if MC_VER == MC_1_16_5
public class ChatTypePolyfill {
    public ChatType chatType;
    public Component name;
    public Component targetName;

    public ChatTypePolyfill(ChatType chatType, Component name, @Nullable Component targetName) {
        this.chatType = chatType;
        this.name = name;
        this.targetName = targetName;
    }
#else
public record ChatTypePolyfill(ChatType chatType, Component name, @Nullable Component targetName) {
#endif
    #if MC_VER < MC_1_19_2
    public ChatTypePolyfill(ChatType bound) {
        this(bound, Component.nullToEmpty(bound.name()), null);
    }
    #else
    public ChatTypePolyfill(ChatType.Bound bound) {
        this(bound.chatType(), bound.name(), bound.targetName());
    }
    #endif
}
