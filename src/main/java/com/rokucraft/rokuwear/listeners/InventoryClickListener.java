package com.rokucraft.rokuwear.listeners;

import com.rokucraft.rokuwear.RokuWear;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    private static final int HAT_SLOT = 39;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getSlotType() == SlotType.ARMOR && !e.getWhoClicked().hasPermission("rokuwear.bypass.armorlock")) {
            ItemStack item = e.getCurrentItem();
            // Allow people to manually remove items like player heads from their hat slot
            if (e.getSlot() == HAT_SLOT && (item == null || item.getType() != RokuWear.HAT_MATERIAL))
                return;
            e.setCancelled(true);
        }
    }
}
