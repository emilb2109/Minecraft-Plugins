package de.thecommunity.thecommunity.listeners;

import de.thecommunity.thecommunity.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FistJoinListener implements Listener {
    public static List<String> licenses = new ArrayList<>();
    private static YamlConfiguration config = FileManager.getCfg2();
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        int cordx = 0;
        int cordy = 0;
        int cordz = 0;
        String world = "";
        String prefix = "NONE";
        if (config.contains("system.prefix")) {
            prefix = (String) config.get("system.prefix");
        }else {config.set("system.prefix", "§8[§6§lServerName§r§8]§r ");
            FileManager.saveAllFiles();
        }
        if (config.contains("system.story.join.cordx")) {
            cordx = (int) config.get("system.story.join.cordx");
        }else {config.set("system.story.join.cordx", 0);
        FileManager.saveAllFiles();
        }
        if (config.contains("system.story.join.cordy")) {
            cordy = (int) config.get("system.story.join.cordy");
        }else {config.set("system.story.join.cordy", 0);
            FileManager.saveAllFiles();
        }
        if (config.contains("system.story.join.cordz")) {
            cordz = (int) config.get("system.story.join.cordz");
        }else {config.set("system.story.join.cordz", 0);
            FileManager.saveAllFiles();
        }
        if (config.contains("system.story.join.world")) {
            world = (String) config.get("system.story.join.world");
        }else {config.set("system.story.join.world", "world");
            FileManager.saveAllFiles();
        }



        Player player = event.getPlayer();
        if (Objects.equals(prefix, "NONE")) {
            player.kickPlayer("Rejoin...");
        }
        else {event.setJoinMessage(prefix + "" + player.getName() + " hat den Server betreten.");}
        if (player.hasPermission("system.join.first")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set system.join.first false");
            player.teleport(new Location(Bukkit.getWorld(world), cordx, cordy, cordz));
        }
    }
    public static String getPrefix() {
        return config.get("system.prefix").toString();
    }
}
