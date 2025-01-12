package de.thecommunity.thecommunity.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Sign implements Listener {
    @EventHandler
    public void onSignEdit(SignChangeEvent event) {
        String s = event.getLine(0);
        if (!event.getPlayer().hasPermission("system.team.sign.place")) return;
            if (s.equalsIgnoreCase("[warp]")) {
                event.setLine(0, "§7[§1Warp§7]");
            }

    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getClickedBlock() == null) return;

        if (event.getClickedBlock().getState() instanceof org.bukkit.block.Sign){
            String warp = ((org.bukkit.block.Sign) event.getClickedBlock().getState()).getLine(1);

            if (warp.equalsIgnoreCase("spawn")) {
                event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
        }
    }

}
