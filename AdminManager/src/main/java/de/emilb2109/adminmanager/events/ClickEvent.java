package de.emilb2109.adminmanager.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;

public class ClickEvent implements Listener {
    List<String> eftog = new ArrayList<String>();

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.RED + "AdminMenu")) {
            //Inventory effectgui = Bukkit.createInventory(player, 9, ChatColor.RED + "Effects Menu");
            switch (event.getCurrentItem().getType()) {
                case DIAMOND_CHESTPLATE:
                    player.closeInventory();
                    player.sendMessage(ChatColor.RED + "Cooming Soon.");
                    break;
                case SPLASH_POTION:
                    player.closeInventory();
                    if (eftog.contains(player.getName())) {
                        player.removePotionEffect(PotionEffectType.JUMP);
                        player.removePotionEffect(PotionEffectType.SATURATION);
                        player.removePotionEffect(PotionEffectType.SPEED);
                        eftog.remove(player.getName());
                        player.sendMessage(ChatColor.RED + "Potions Deactive.");
                        break;
                    }
                    if (!(eftog.contains(player.getName()))) {
                        final PotionEffect applyEffect = new PotionEffect(PotionEffectType.JUMP, 9999, 3);
                        player.addPotionEffect(applyEffect, true);
                        final PotionEffect applyEffect2 = new PotionEffect(PotionEffectType.SPEED, 9999, 3);
                        player.addPotionEffect(applyEffect2, true);
                        final PotionEffect applyEffect3 = new PotionEffect(PotionEffectType.SATURATION, 9999, 10);
                        player.addPotionEffect(applyEffect3, true);
                        eftog.add(player.getName());
                        player.sendMessage(ChatColor.RED + "Potions Active.");
                        break;
                    }

                    break;
            }
            event.setCancelled(true);
        }
    }
}
