package com.rokucraft.rokuwear.config;

import com.rokucraft.rokuwear.RokuWear;
import com.rokucraft.rokuwear.config.serializers.ComponentSerializer;
import com.rokucraft.rokuwear.wearables.ArmorSet;
import com.rokucraft.rokuwear.wearables.Hat;
import net.kyori.adventure.text.Component;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Config {
    private final List<Hat> hats = new ArrayList<>();
    private final List<ArmorSet> armorSets = new ArrayList<>();

    public Config(RokuWear plugin) {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        try {
            YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                    .file(new File(plugin.getDataFolder(), "config.yml"))
                    .defaultOptions(opts -> opts.serializers(
                            build -> build.register(Component.class, ComponentSerializer.INSTANCE)
                    )).build();
            ConfigurationNode root = loader.load();

            this.hats().addAll(root.node("hats").getList(Hat.class, new ArrayList<>()));
            this.armorSets().addAll(root.node("armor").getList(ArmorSet.class, new ArrayList<>()));

            // Generate a new config file if one doesn't exist yet
            if (!configFile.exists())
                loader.save(root);

        } catch (ConfigurateException e) {
            plugin.getLogger().severe("Unable to load config!");
            plugin.getLogger().severe(e.getMessage());
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    public List<Hat> hats() {
        return hats;
    }

    public List<ArmorSet> armorSets() {
        return armorSets;
    }

}
