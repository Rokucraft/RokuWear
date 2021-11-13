package com.rokucraft.rokuwear.commands;

import cloud.commandframework.ArgumentDescription;
import cloud.commandframework.paper.PaperCommandManager;
import com.rokucraft.rokuwear.RokuWear;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand {
    private final RokuWear plugin;

    public ReloadCommand(RokuWear plugin) {
        this.plugin = plugin;
    }

    public void register(PaperCommandManager<CommandSender> manager) {
        manager.command(
                manager.commandBuilder("rokuwear")
                        .literal("reload", ArgumentDescription.of("Reload RokuWear"))
                        .permission("rokuwear.reload")
                        .handler(commandContext -> {
                                    plugin.loadConfig();
                                    commandContext.getSender().sendMessage(Component.text().append(
                                            RokuWear.PREFIX,
                                            Component.text("Successfully reloaded the config!", NamedTextColor.GREEN)
                                    ));
                                }
                        )
        );
    }
}
