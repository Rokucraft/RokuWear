package com.rokucraft.rokuwear.commands;

import com.rokucraft.rokuwear.RokuWear;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.incendo.cloud.CommandManager;

import static org.incendo.cloud.description.Description.description;

public class ReloadCommand {
    private final RokuWear plugin;

    public ReloadCommand(RokuWear plugin) {
        this.plugin = plugin;
    }

    public void register(CommandManager<CommandSender> manager) {
        manager.command(
                manager.commandBuilder("rokuwear")
                        .literal("reload", description("Reload RokuWear"))
                        .permission("rokuwear.reload")
                        .handler(commandContext -> {
                                    plugin.loadConfig();
                                    commandContext.sender().sendMessage(Component.text().append(
                                            RokuWear.PREFIX,
                                            Component.text("Successfully reloaded the config!", NamedTextColor.GREEN)
                                    ));
                                }
                        )
        );
    }
}
