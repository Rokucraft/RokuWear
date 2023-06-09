package com.rokucraft.rokuwear.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;

public class Heads {
    public static final ItemStack NEXT_ARROW_HEAD = getHead(
            Component.text("Next", NamedTextColor.YELLOW),
            "Oak Wood Arrow Right",
            "d513d666-0992-42c7-9aa6-e518a83e0b38",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTliZjMyOTJlMTI2YTEwNWI1NGViYTcxM2FhMWIxNTJkNTQxYTFkODkzODgyOWM1NjM2NGQxNzhlZDIyYmYifX19");

    public static final ItemStack PREVIOUS_ARROW_HEAD = getHead(
            Component.text("Previous", NamedTextColor.YELLOW),
            "Oak Wood Arrow Left",
            "2391d533-ab09-434d-9980-adafde4057a3",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmQ2OWUwNmU1ZGFkZmQ4NGU1ZjNkMWMyMTA2M2YyNTUzYjJmYTk0NWVlMWQ0ZDcxNTJmZGM1NDI1YmMxMmE5In19fQ==");

    public static final ItemStack X_HEAD = getHead(
            "Oak Wood X",
            "33b5946c-20d1-466f-8dd5-11a0fdcd7105",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2Nzg3YmEzMjU2NGU3YzJmM2EwY2U2NDQ5OGVjYmIyM2I4OTg0NWU1YTY2YjVjZWM3NzM2ZjcyOWVkMzcifX19");

    private static @NonNull ItemStack getHead(Component name, String profileName, String uuid, String texturesValue) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        PlayerProfile profile = Bukkit.createProfile(UUID.fromString(uuid), profileName);
        profile.setProperty(new ProfileProperty("textures", texturesValue));
        skullMeta.setPlayerProfile(profile);
        if (name != null)
            skullMeta.displayName(name);
        skull.setItemMeta(skullMeta);
        return skull;
    }

    private static @NonNull ItemStack getHead(String profileName, String uuid, String texturesValue) {
        return getHead(null, profileName, uuid, texturesValue);
    }
}
