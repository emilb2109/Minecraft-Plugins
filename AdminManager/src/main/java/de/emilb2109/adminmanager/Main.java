package de.emilb2109.adminmanager;

import de.emilb2109.adminmanager.commands.adminCommand;
import de.emilb2109.adminmanager.commands.adutyCommand;
import de.emilb2109.adminmanager.events.ClickEvent;
import de.emilb2109.adminmanager.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    FileConfiguration config = getConfig();
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_RED + "AdminManager Loaded.");
        config.addDefault("commands.aduty.enable", true);
        saveConfig();

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new JoinListener(), this);

        getCommand("aduty").setExecutor(new adutyCommand());
        getCommand("admin").setExecutor(new adminCommand());
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
