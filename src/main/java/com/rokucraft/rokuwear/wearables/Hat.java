package com.rokucraft.rokuwear.wearables;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@ConfigSerializable
public record Hat(Component name, List<Component> lore, int customModelData, String permission) {
    public @NonNull ItemStack toItemStack() {
        ItemStack item = new ItemStack(Material.SCUTE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(name);
        itemMeta.setCustomModelData(customModelData);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }
}

