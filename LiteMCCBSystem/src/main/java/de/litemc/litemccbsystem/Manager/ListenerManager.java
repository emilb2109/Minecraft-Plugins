package de.litemc.litemccbsystem.Manager;

import de.litemc.litemccbsystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class ListenerManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);

        if (!Objects.equals(Main.getInstance().getCustomConfig().getString("General.JoinMessage"), "")) {
            Bukkit.broadcastMessage(Main.getInstance().getCustomConfig().getString("General.JoinMessage").replace("&", "§").replace("%player%", e.getPlayer().getName()));
        }

        Main.getInstance().getPlayerManager().addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {;
        e.setQuitMessage(null);

        if (!Objects.equals(Main.getInstance().getCustomConfig().getString("General.QuitMessage"), "")) {
            //e.getPlayer().sendMessage(Main.getInstance().getCustomConfig().getString("General.QuitMessage").replace("&", "§").replace("%player%", e.getPlayer().getName()));
            Bukkit.broadcastMessage(Main.getInstance().getCustomConfig().getString("General.QuitMessage").replace("&", "§").replace("%player%", e.getPlayer().getName()));
        }
    }

    @EventHandler
    public void onPlayerChat(PlayerChatEvent e) {
        if(e.getMessage().equals("lite!info5")) {
            e.getPlayer().sendMessage("§cThis Server is running §eLiteMC System Script §cby §cemilb2109§c.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEnterPortal(PlayerPortalEvent e) {
        e.setCancelled(true);
    }

}
