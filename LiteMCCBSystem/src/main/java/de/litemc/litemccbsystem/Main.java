package de.litemc.litemccbsystem;

import de.litemc.litemccbsystem.Manager.CCommand;
import de.litemc.litemccbsystem.Manager.ListenerManager;
import de.litemc.litemccbsystem.Manager.Menu.Listener.MenuListener;
import de.litemc.litemccbsystem.Manager.Menu.System.PlayerMenuUtility;
import de.litemc.litemccbsystem.Manager.PermissionManager;
import de.litemc.litemccbsystem.Manager.PlayerManager;
import de.litemc.litemccbsystem.commands.ALCommand;
import de.litemc.litemccbsystem.commands.HelpCommand;
import de.litemc.litemccbsystem.commands.WarpCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

import static de.litemc.litemccbsystem.Manager.PermissionManager.setPermission;

public final class Main extends JavaPlugin {

    private static Main instance;
    private File file;
    private YamlConfiguration yamlConfiguration;
    private static SimpleCommandMap scm;
    private SimplePluginManager spm;

    private PermissionManager permissionManager;

    private PlayerManager playerManager;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        permissionManager = new PermissionManager();
        playerManager = new PlayerManager();
        setupSimpleCommandMap();
        LoadConfig();
        setPermission(getConfig().getString("Commands.HelpCommand.Permission"));
        if (Main.getInstance().getCustomConfig().getString("Commands.HelpCommand.Command") == null || Main.getInstance().getCustomConfig().getString("Commands.HelpCommand.Command").isEmpty()) {
            Main.getInstance().getCustomConfig().set("Commands.HelpCommand.Command", "help");
            Main.getInstance().saveConfig();
        }
        setPermission(getConfig().getString("Commands.ALCommand.Permission"));
        registerCommands(new ALCommand(), new HelpCommand(), new WarpCommand());
        Bukkit.getPluginManager().registerEvents(new ListenerManager(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void LoadConfig() {
        String configVersion = "1.0";

        saveDefaultConfig();

        if (!Objects.equals(getConfig().getString("configVersion"), configVersion)) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
            getLogger().info("Config updated to version " + configVersion);
        }

        file = new File(getDataFolder(), "players.yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.options().copyDefaults(true);
        yamlConfiguration.addDefault("players", new ArrayList<>());
    }

    @Override
    public void onDisable() {
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p) {
        PlayerMenuUtility playerMenuUtility;
        if (!(playerMenuUtilityMap.containsKey(p))) {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);

            return playerMenuUtility;
        } else {
            return playerMenuUtilityMap.get(p);
        }
    }

    private void registerCommands(CCommand... commands) {
        Arrays.stream(commands).forEach(command -> scm.register("pluginname", command));
    }

    private void setupSimpleCommandMap() {
        spm = (SimplePluginManager) this.getServer().getPluginManager();
        Field f = null;
        try {
            f = SimplePluginManager.class.getDeclaredField("commandMap");
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            scm = (SimpleCommandMap) f.get(spm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SimpleCommandMap getCommandMap() {
        return scm;
    }

    public FileConfiguration getCustomConfig() {
        return getConfig();
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public YamlConfiguration getPlayerConfig() {
        return yamlConfiguration;
    }

    public void savePlayerFile() {
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public String getPrefix() {
        if (getConfig().getString("Prefix") == null || getConfig().getString("Prefix").isEmpty()) {
            return "§8[§6LiteMC.de§8] §7";
        } else {
            return getConfig().getString("Prefix").replace("&", "§");
        }
    }

    public void saveCustomConfig() {
        saveConfig();
    }

    public void reloadCustomConfig() {
        reloadConfig();
    }

}
