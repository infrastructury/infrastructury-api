package net.minecraft.world.level.storage.loot;

#if MC_VER < MC_1_20_1
import com.mojang.serialization.Codec;

public class LootDataType<T> {
    private final Codec<T> codec;
    private final String directory;
    private final LootDataType.Validator<T> validator;

    private LootDataType(Codec<T> var1, String var2, LootDataType.Validator<T> var3) {
        this.codec = var1;
        this.directory = var2;
        this.validator = var3;
    }

    public String directory() {
        return this.directory;
    }

    @FunctionalInterface
    public interface Validator<T> {
        void run(ValidationContext var1, LootDataId<T> var2, T var3);
    }
}
#endif
