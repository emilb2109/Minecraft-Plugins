package de.emilb2109.supportsystem;

import de.emilb2109.supportsystem.commands.ticket;
import de.emilb2109.supportsystem.util.Method;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Main extends JavaPlugin {

    private static Main instance;

    private File file;
    private YamlConfiguration yamlConfiguration;

    private String prefix;

    private Method method;

    @Override
    public void onEnable() {
        instance = this;

        prefix = "§7[§9Support System§7]";
        method = new Method();

        loadConfig();

        getCommand("ticket").setExecutor(new ticket());
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
        yamlConfiguration.addDefault("license", "YOUR-LICENSE-HERE"); // Not used
        yamlConfiguration.addDefault("prefix", "&7[&9Support System&7]");
        yamlConfiguration.addDefault("logsWebhook", "WEBHOOK-URL");
        yamlConfiguration.addDefault("ticketWebhook", "WEBHOOK-URL");
        yamlConfiguration.addDefault("enable", true);
        yamlConfiguration.addDefault("mysql.host", "localhost");
        yamlConfiguration.addDefault("mysql.port", 3306);
        yamlConfiguration.addDefault("mysql.user", "user");
        yamlConfiguration.addDefault("mysql.password", "password");
        yamlConfiguration.addDefault("mysql.database", "database");
        yamlConfiguration.addDefault("mysql.enable", false);

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prefix = ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("prefix"));
    }

    @Override
    public void onDisable() {
        getMethod().discordMessage("Server", "Alle Tickets sind geschlossen!");
    }

    public static Main getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

    public YamlConfiguration getConfig() {
        return yamlConfiguration;
    }

    public String getPrefix() {
        return prefix;
    }
}
