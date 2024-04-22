package com.mrmelon54.infrastructury.registry.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.jetbrains.annotations.Nullable;

public interface ExtendedMenuProvider extends MenuProvider {
    default remapped.architectury.registry.menu.ExtendedMenuProvider convert() {
        return new remapped.architectury.registry.menu.ExtendedMenuProvider() {
            @Override
            public void saveExtraData(FriendlyByteBuf friendlyByteBuf) {
                ExtendedMenuProvider.this.saveExtraData(friendlyByteBuf);
            }

            @Override
            public Component getDisplayName() {
                return ExtendedMenuProvider.this.getDisplayName();
            }

            @Nullable
            @Override
            public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
                return ExtendedMenuProvider.this.createMenu(i, inventory, player);
            }
        };
    }

    void saveExtraData(FriendlyByteBuf buf);
}
