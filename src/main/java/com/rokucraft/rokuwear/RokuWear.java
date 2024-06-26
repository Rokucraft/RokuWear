package com.rokucraft.rokuwear;

import com.rokucraft.rokuwear.commands.ArmorCommand;
import com.rokucraft.rokuwear.commands.HatsCommand;
import com.rokucraft.rokuwear.commands.ReloadCommand;
import com.rokucraft.rokuwear.config.Config;
import com.rokucraft.rokuwear.listeners.InventoryClickListener;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

public final class RokuWear extends JavaPlugin {
    public static final Material HAT_MATERIAL = Material.SCUTE;
    private Config config = new Config(this);
    private PaperCommandManager<CommandSender> manager;
    public static final Component PREFIX = Component.text("[", NamedTextColor.DARK_GRAY)
            .append(Component.text("RokuWear", NamedTextColor.GOLD))
            .append(Component.text("] ", NamedTextColor.DARK_GRAY));

    @Override
    public void onEnable() {
        try {
            this.manager = PaperCommandManager.createNative(this, ExecutionCoordinator.simpleCoordinator());
        } catch (final Exception e) {
            this.getLogger().severe("Failed to initialize the command manager");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        new ArmorCommand(this).register(manager);
        new HatsCommand(this).register(manager);
        new ReloadCommand(this).register(manager);
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
    }


    public void loadConfig() {
        config = new Config(this);
    }

    @NonNull
    public Config config() {
        return config;
    }
}
