package com.mrmelon54.OmniPlay.event.events.common;

import com.mrmelon54.OmniPlay.event.EventWrapper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.chunk.ChunkAccess;
import org.jetbrains.annotations.Nullable;
import remapped.architectury.event.Event;

public interface ChunkEvent {
    interface Inner extends remapped.architectury.event.events.common.ChunkEvent {
    }

    Event<SaveData> SAVE_DATA = EventWrapper.of(Inner.SAVE_DATA, saveData -> saveData::save);
    Event<LoadData> LOAD_DATA = EventWrapper.of(Inner.LOAD_DATA, loadData -> loadData::load);

    interface SaveData {
        void save(ChunkAccess chunk, ServerLevel level, CompoundTag nbt);
    }

    interface LoadData {
        void load(ChunkAccess chunk, @Nullable ServerLevel level, CompoundTag nbt);
    }
}
