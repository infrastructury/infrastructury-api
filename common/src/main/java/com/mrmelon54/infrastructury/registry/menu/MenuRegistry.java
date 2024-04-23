package com.mrmelon54.infrastructury.registry.menu;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Consumer;

public final class MenuRegistry {
    private MenuRegistry() {
    }

    public static void openExtendedMenu(ServerPlayer player, MenuProvider provider, Consumer<FriendlyByteBuf> bufWriter) {
        remapped.architectury.registry.menu.MenuRegistry.openExtendedMenu(player, provider, bufWriter);
    }

    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider provider) {
        remapped.architectury.registry.menu.MenuRegistry.openExtendedMenu(player, provider.convert());
    }

    public static void openMenu(ServerPlayer player, MenuProvider provider) {
        player.openMenu(provider);
    }

    public static <T extends AbstractContainerMenu> MenuType<T> ofExtended(ExtendedMenuTypeFactory<T> factory) {
        return remapped.architectury.registry.menu.MenuRegistry.ofExtended(factory::create);
    }

    @Environment(EnvType.CLIENT)
    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreenFactory(MenuType<? extends H> type, ScreenFactory<H, S> factory) {
        remapped.architectury.registry.menu.MenuRegistry.registerScreenFactory(type, factory::create);
    }

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface ScreenFactory<H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> {
        S create(H containerMenu, Inventory inventory, Component component);
    }

    @FunctionalInterface
    public interface ExtendedMenuTypeFactory<T extends AbstractContainerMenu> {
        T create(int i, Inventory inventory, FriendlyByteBuf friendlyByteBuf);
    }
}
