package de.emilb2109.adminmanager.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("system.team.join.message")) {
            player.sendMessage(ChatColor.RED + "Du bist nicht im Admin Dients. Mit /aduty gehst du in den Admin Dients.");
        }
    }
}
