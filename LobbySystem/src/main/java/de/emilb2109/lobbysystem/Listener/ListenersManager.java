package de.emilb2109.lobbysystem.Listener;

import com.google.common.collect.Lists;
import de.emilb2109.lobbysystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class ListenersManager implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        String joinMessage = Main.getInstance().getConfig().getString("joinMessageContent").replace("%1%", Main.getInstance().getPrefix()).replace("%2%", event.getPlayer().getDisplayName());
        Player player = event.getPlayer();
        if(Main.getInstance().getConfig().getBoolean("joinMessage")) {
            Bukkit.getOnlinePlayers().forEach(players -> {
                players.sendMessage(joinMessage);
            });
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.setHealth(1);
        player.setMaxHealth(1);
        player.setFoodLevel(20);
        ItemStack navigator = new ItemStack(Material.COMPASS);
        ItemMeta navigatorMeta = navigator.getItemMeta();
        navigatorMeta.setDisplayName("§aNavigator");
        navigator.setItemMeta(navigatorMeta);
        player.getInventory().setItem(1, navigator);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        if(Main.getInstance().getConfig().getBoolean("quitMessage")) {
            Bukkit.getOnlinePlayers().forEach(players -> {
                players.sendMessage(Main.getInstance().getPrefix() + " §7" + player.getDisplayName() + " §7hat den Server verlassen.");
            });
        }
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().clear();
        player.setHealth(1);
        player.setFoodLevel(20);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(!Main.getInstance().getPlayerManager().isPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(!Main.getInstance().getPlayerManager().isPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public  void onDrop(PlayerDropItemEvent event) {
        if(!Main.getInstance().getPlayerManager().isPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(!Main.getInstance().getPlayerManager().isPlayer((Player) event.getWhoClicked())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
            event.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
            event.setCancelled(true);
    }

    @EventHandler
    public void onInter(PlayerInteractEvent event) {
        if(!Main.getInstance().getPlayerManager().isPlayer(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

}
