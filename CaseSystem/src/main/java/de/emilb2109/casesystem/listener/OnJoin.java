package de.emilb2109.casesystem.listener;

import de.emilb2109.casesystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnJoin implements Listener {

    @EventHandler
    public void OnJoin(PlayerJoinEvent event) {
        Main.getInstance().getStatsManager().loadPlayerStats(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void OnQuit(PlayerQuitEvent event) {
        Main.getInstance().getStatsManager().unloadPlayerStats(event.getPlayer().getUniqueId());
    }
}
