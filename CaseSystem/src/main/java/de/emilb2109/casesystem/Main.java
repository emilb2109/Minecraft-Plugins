package de.emilb2109.casesystem;

import de.emilb2109.casesystem.commands.Case;
import de.emilb2109.casesystem.listener.OnJoin;
import de.emilb2109.casesystem.listener.PlayerInteractListener;
import de.emilb2109.casesystem.manager.DatabaseManager;
import de.emilb2109.casesystem.manager.StatsManager;
import de.emilb2109.casesystem.util.Method;
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

    private DatabaseManager databaseManager;

    private StatsManager statsManager;

    private Method method;

    @Override
    public void onEnable() {
        instance = this;

        prefix = "§7[§9Case System§7]";

        loadConfig();

        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);

        databaseManager = new DatabaseManager();

        statsManager = new StatsManager();

        method = new Method();

        getCommand("case").setExecutor(new Case());

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
        yamlConfiguration.addDefault("prefix", "&7[&9Spielmodus&7]");
        yamlConfiguration.addDefault("mysql.host", "localhost");
        yamlConfiguration.addDefault("mysql.port", 3306);
        yamlConfiguration.addDefault("mysql.user", "user");
        yamlConfiguration.addDefault("mysql.password", "password");
        yamlConfiguration.addDefault("mysql.database", "database");

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prefix = ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("prefix"));
    }

    @Override
    public void onDisable() {
        getDatabaseManager().shutDownDatasource();
    }

    public static Main getInstance() {
        return instance;
    }

    public String getPrefix() {
        return prefix;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public File getFile() {
        return file;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public StatsManager getStatsManager() {
        return statsManager;
    }

    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }
}
