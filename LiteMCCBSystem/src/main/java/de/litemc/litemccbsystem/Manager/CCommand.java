package de.litemc.litemccbsystem.Manager;

import de.litemc.litemccbsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

@SuppressWarnings("all")
public abstract class CCommand extends Command implements PluginIdentifiableCommand {
    CommandSender sender;//So you can mess with this inside this class
    Main plugin = Main.getInstance();
    protected CCommand(String name, String description, String usageMessage, String... aliases) {
        super(name, description, usageMessage, Arrays.asList(aliases));
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    public abstract void run(CommandSender sender, String commandLabel, String[] arguments);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] arguments) {
        this.sender = sender;
        run(sender, commandLabel, arguments);
        return true;
    }

    protected void sendMessage(String... messages) {
        Arrays.stream(messages)
                .forEach(message -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
    protected void sendMessage(CommandSender sender, String... messages) {
        Arrays.stream(messages)
                .forEach(message -> sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }
}