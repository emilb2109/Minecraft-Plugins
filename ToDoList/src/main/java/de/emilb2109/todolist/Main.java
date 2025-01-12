package de.emilb2109.todolist;

import de.emilb2109.todolist.Commands.ToDoCommand;
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
    private YamlConfiguration yamlConfiguration2;

    private String prefix;

    @Override
    public void onEnable() {

        prefix = "§7[§9ToDo List§7]";

        loadConfig();
        loadToDo();

        getCommand("todo").setExecutor(new ToDoCommand());

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
        yamlConfiguration.addDefault("prefix", "&7[&9ToDo List&7]");
        yamlConfiguration.addDefault("command.todo", "true");

        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        prefix = ChatColor.translateAlternateColorCodes('&', yamlConfiguration.getString("prefix") + " ");
    }

    private void loadToDo() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        file = new File(getDataFolder(), "ToDo.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        yamlConfiguration2 = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration2.options().copyDefaults(true);
        yamlConfiguration2.addDefault("todo.test", "Test");

        try {
            yamlConfiguration2.save(file);
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

    public YamlConfiguration getToDo() {
        return yamlConfiguration2;
    }

    public String getPrefix() {
        return prefix;
    }
}
