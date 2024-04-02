package net.minecraft.world.level.storage.loot;

#if MC_VER > MC_1_16_5 && MC_VER < MC_1_20_1
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class LootDataManager implements PreparableReloadListener, LootDataResolver {
    private final LootTables lootDataManager;

    public LootDataManager(LootTables lootDataManager) {
        this.lootDataManager = lootDataManager;
    }

    public final CompletableFuture<Void> reload(PreparableReloadListener.PreparationBarrier preparationBarrier, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        return lootDataManager.reload(preparationBarrier, resourceManager, preparationsProfiler, reloadProfiler, backgroundExecutor, gameExecutor);
    }

    @Override
    public <T> @Nullable T getElement(LootDataId<T> var1) {
        return (T) lootDataManager.get(var1.location());
    }
}
#endif
