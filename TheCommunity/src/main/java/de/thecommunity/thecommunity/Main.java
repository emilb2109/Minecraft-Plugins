package de.thecommunity.thecommunity;
import de.thecommunity.thecommunity.listeners.FistJoinListener;
import de.thecommunity.thecommunity.listeners.Sign;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;



public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        String prefix = "[]";
        FileManager.setup();

        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new FistJoinListener(), this);
        manager.registerEvents(new Sign(), this);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
