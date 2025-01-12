package de.emilb2109.lobbysystem;

import de.emilb2109.lobbysystem.Commands.BuildCommand;
import de.emilb2109.lobbysystem.Listener.ListenersManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;

    private String prefix;

    private File file;
    private YamlConfiguration yamlConfiguration;

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;
        prefix = "§7[§9Lobby§7]";

        loadConfig();

        playerManager = new PlayerManager();

        Bukkit.getPluginManager().registerEvents(new ListenersManager(), this);

        getCommand("build").setExecutor(new BuildCommand());
    }

    private void loadConfig() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.options().copyDefaults(true);
        yamlConfiguration.addDefault("joinMessage", true);
        yamlConfiguration.addDefault("joinMessageContent", "%1% §7%2% §7hat den Server betreten.");
        yamlConfiguration.addDefault("quitMessage", true);
        yamlConfiguration.addDefault("prefix", "&7[&9Lobby&7]");

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prefix = ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("prefix"));
    }

    @Override
    public void onDisable() {
    }

    public static Main getInstance() {
        return instance;
    }

    public String getPrefix() {
        return prefix;
    }

    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
