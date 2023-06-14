package com.rokucraft.rokuwear.wearables;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public record ArmorSet(Piece chestplate, Piece leggings, Piece boots, String permission) {
    private static final AttributeModifier NO_ARMOR_MODIFIER =
            new AttributeModifier(Attribute.GENERIC_ARMOR.name(), 0, AttributeModifier.Operation.ADD_NUMBER);
    private static final AttributeModifier NO_ARMOR_TOUGHNESS_MODIFIER =
            new AttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS.name(), 0, AttributeModifier.Operation.ADD_NUMBER);

    @ConfigSerializable
    public record Piece(Component name, List<Component> lore, Material material) {
        public @NonNull ItemStack toItemStack() {
            if (material == null)
                return new ItemStack(Material.AIR);
            ItemStack item = new ItemStack(material);
            item.editMeta(meta -> {
                meta.displayName(name);
                meta.lore(lore);
                meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR, NO_ARMOR_MODIFIER);
                meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, NO_ARMOR_TOUGHNESS_MODIFIER);
            });
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
