package com.rokucraft.rokuwear.util;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GuiUtil {
    @NotNull
    public static StaticPane getNavigation(ChestGui gui, PaginatedPane pages) {
        StaticPane navigation = new StaticPane(0, gui.getRows() - 1, 9, 1);

        navigation.addItem(new GuiItem(Heads.PREVIOUS_ARROW_HEAD, event -> {
            if (pages.getPage() > 0) {
                pages.setPage(pages.getPage() - 1);
                gui.update();
            }
        }), 0, 0);

        navigation.addItem(new GuiItem(Heads.NEXT_ARROW_HEAD, event -> {
            if (pages.getPage() < pages.getPages() - 1) {
                pages.setPage(pages.getPage() + 1);
                gui.update();
            }
        }), 8, 0);
        return navigation;
    }

    public static StaticPane getHeader(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwningPlayer(player);
        meta.displayName(player.displayName());
        head.setItemMeta(meta);

        StaticPane header = new StaticPane(9,1);
        header.addItem(new GuiItem(head, event -> player.performCommand("me")), 4, 0);
        return header;
    }

    public static StaticPane getRemoveButton(String label, int x, int y, Consumer<InventoryClickEvent> action) {
        ItemStack button = Heads.X_HEAD;
        ItemMeta meta = button.getItemMeta();
        meta.displayName(Component.text(label, NamedTextColor.RED));
        button.setItemMeta(meta);

        StaticPane pane = new StaticPane(x, y, 1,1);
        pane.addItem(new GuiItem(button, action), 0, 0);
        return pane;
    }
}
