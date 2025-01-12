package de.emilb2109.casesystem.listener;

import de.emilb2109.casesystem.Main;
import de.emilb2109.casesystem.manager.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.EnderChest;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerInteractListener implements Listener {

    private ArrayList<Location> touchedLocations = new ArrayList<>();

    private Inventory inv;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.getBlock().getType().equals(Material.END_PORTAL_FRAME)) {
            Bukkit.broadcastMessage("3");
            touchedLocations.add(event.getBlockPlaced().getLocation());
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(event.getClickedBlock().getType() == Material.END_PORTAL_FRAME) {
                addStartItems(event.getPlayer(), event.getPlayer().getDisplayName());
            }
        }
            if (event.getClickedBlock() != null) {
                if (event.getClickedBlock().getState() instanceof EndPortalFrame) {
                    addStartItems(event.getPlayer(), event.getPlayer().getDisplayName());
                }
            }
    }

    private void addStartItems(Player player, String displayname) {
        inv = Bukkit.createInventory(null, 9, "§6§lCases");

        ItemStack vote_chest = new ItemStack(Material.CHEST);
        ItemMeta vote_chest_meta = vote_chest.getItemMeta();
        vote_chest_meta.setDisplayName("§eVote§7 Case");
        List<String> vote_lore = new ArrayList<String>();
        PlayerStats playerStats = Main.getInstance().getStatsManager().getPlayerStats(player.getUniqueId());
        vote_lore.add("§7Du hast noch §a" + playerStats.getVote() + "§7 von diesen Cases.");
        vote_chest_meta.setLore(vote_lore);
        vote_chest.setItemMeta(vote_chest_meta);
        inv.setItem(2, vote_chest);

        ItemStack suprem_chest = new ItemStack(Material.ENDER_CHEST);
        ItemMeta suprem_chest_meta = suprem_chest.getItemMeta();
        suprem_chest_meta.setDisplayName("§bSupreme§7 Case");
        List<String> supreme_lore = new ArrayList<String>();
        supreme_lore.add("§7Du hast noch §a" + playerStats.getSupreme() + "§7 von diesen Cases.");
        suprem_chest_meta.setLore(supreme_lore);
        suprem_chest.setItemMeta(suprem_chest_meta);
        inv.setItem(4, suprem_chest);

        ItemStack mega_chest = new ItemStack(Material.ENDER_CHEST);
        ItemMeta mega_chest_meta = mega_chest.getItemMeta();
        mega_chest_meta.setDisplayName("§cMega§7 Case");
        List<String> mega_lore = new ArrayList<String>();
        mega_lore.add("§7Du hast noch §a" + playerStats.getMega() + " §7von diesen Cases.");
        mega_chest_meta.setLore(mega_lore);
        mega_chest.setItemMeta(mega_chest_meta);
        inv.setItem(6, mega_chest);
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();
        final String pd = ((Player) e.getWhoClicked()).getPlayer().getDisplayName();

        // Using slots click is a best option for your inventory click's
        PlayerStats playerStats = Main.getInstance().getStatsManager().getPlayerStats(p.getUniqueId());
        if (e.getRawSlot() == 2) {
            if (playerStats.getVote() > 0) {
                p.sendMessage("Open Case Vote");
                playerStats.setVote(playerStats.getVote() -1);
                Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                p.closeInventory();
            }
        }

        if (e.getRawSlot() == 4) {
            if (playerStats.getSupreme() > 0) {
                p.sendMessage("Open Case Supreme");
                playerStats.setSupreme(playerStats.getSupreme() -1);
                Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                p.closeInventory();
            }
        }

        if (e.getRawSlot() == 6) {
            if (playerStats.getMega() > 0) {
                p.sendMessage("Open Case Mega");
                playerStats.setMega(playerStats.getMega() -1);
                Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                p.closeInventory();
            }
        }
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }

}
