package de.emilb2109.eveloator;

import de.emilb2109.eveloator.Listeners.PlayerMove;
import de.emilb2109.eveloator.Listeners.PlayerToggleSneak;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerMove(), this);

        pluginManager.registerEvents(new PlayerToggleSneak(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
