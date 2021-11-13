package com.rokucraft.rokuwear.wearables;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public record ArmorSet(Piece chestplate, Piece leggings, Piece boots, String permission) {
    @ConfigSerializable
    public record Piece(Component name, List<Component> lore, Material material) {
        public @NonNull ItemStack toItemStack() {
            if (material == null)
                return new ItemStack(Material.AIR);
            ItemStack item = new ItemStack(material);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.displayName(name);
            itemMeta.lore(lore);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(itemMeta);
            return item;
        }
    }

    public List<Piece> pieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(chestplate());
        pieces.add(leggings());
        pieces.add(boots());
        return pieces;
    }
}
