package com.rokucraft.rokuwear.commands;

import com.github.stefvanschie.inventoryframework.adventuresupport.ComponentHolder;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.util.Slot;
import com.rokucraft.rokuwear.RokuWear;
import com.rokucraft.rokuwear.util.GuiUtil;
import com.rokucraft.rokuwear.wearables.Hat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.incendo.cloud.CommandManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static org.incendo.cloud.description.Description.description;

public class HatsCommand {
    private final RokuWear plugin;

    public HatsCommand(RokuWear plugin) {
        this.plugin = plugin;
    }

    public void register(CommandManager<CommandSender> manager) {
        manager.command(
                manager.commandBuilder("hats", description("Shows your available hats"))
                        .senderType(Player.class)
                        .handler(context -> {
                                    final Player player = context.sender();
                                    List<GuiItem> guiItemList = new ArrayList<>();
                                    for (Hat hat : plugin.config().hats())
                                        if (hat.permission() == null || player.hasPermission(hat.permission()))
                                            guiItemList.add(guiItem(hat));

                                    if (guiItemList.isEmpty())
                                        player.sendMessage(Component.text("You do not own any hats!", NamedTextColor.RED));
                                    else
                                        buildGui(guiItemList, player).show(player);
                                }
                        )
        );
    }

    @NotNull
    private ChestGui buildGui(List<GuiItem> guiItemList, Player player) {
        ChestGui gui = new ChestGui(5, ComponentHolder.of(Component.text("Hats")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane pages = new PaginatedPane(7, gui.getRows() - 2);
        pages.populateWithGuiItems(guiItemList);
        gui.addPane(Slot.fromXY(1, 1), pages);
        GuiUtil.addHeader(gui, player);
        GuiUtil.addRemoveButton(
                gui, "Remove Hat", 4, gui.getRows() - 1,
                event -> {
                    event.getWhoClicked().getInventory().setHelmet(null);
                    event.getWhoClicked().sendMessage(Component.text("Your hat was removed", NamedTextColor.GREEN));
                }
        );
        if (pages.getPages() > 1)
            GuiUtil.addNavigation(gui, pages);

        return gui;
    }

    @NotNull
    private GuiItem guiItem(Hat hat) {
        ItemStack hatItem = hat.toItemStack();
        return new GuiItem(hatItem,
                event -> {
                    event.getWhoClicked().getInventory().setHelmet(hatItem);
                    event.getWhoClicked().sendMessage(Component.text().append(
                            Component.text("Equipped ", NamedTextColor.GREEN),
                            hat.name().hoverEvent(hatItem.asHoverEvent())
                    ));
                }
        );
    }
}
