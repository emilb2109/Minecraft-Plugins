package de.litemc.cloudnetsaveserverininterval;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private static Main instance;

    private File file;
    private YamlConfiguration yamlConfiguration;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();

        IntervalManager intervalManager = new IntervalManager();

        intervalManager.start();
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
        yamlConfiguration.addDefault("serverName", "CityBuild1-1");
        yamlConfiguration.addDefault("interval", 60);

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }

    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }
}
