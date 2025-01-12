package de.emilb2109.adminmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class adminCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("system.team.admin.command")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                    switch (args[0].toLowerCase()) {
                        case "help":
                            sender.sendMessage(ChatColor.RED + "Syntex: ");
                            sender.sendMessage(ChatColor.GOLD + "/admin menu" + ChatColor.RED + " for a Admin Menu.");
                            break;
                        case "menu":
                            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.RED + "AdminMenu");
                            ItemStack eqipment = new ItemStack(Material.DIAMOND_CHESTPLATE);
                            ItemStack effects = new ItemStack(Material.SPLASH_POTION);
                            ItemStack none = new ItemStack(Material.GRAY_STAINED_GLASS);

                            ItemMeta eqipment_meta = eqipment.getItemMeta();
                            eqipment_meta.setDisplayName(ChatColor.RED + "Equipment");
                            eqipment_meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            eqipment_meta.addEnchant(Enchantment.DURABILITY, 1, true);
                            ArrayList<String> eqipment_lore = new ArrayList<>();
                            eqipment_lore.add(ChatColor.GOLD + "Admin Armor, Bow, Sword, etc.");
                            eqipment_meta.setLore(eqipment_lore);
                            eqipment.setItemMeta(eqipment_meta);

                            ItemMeta effect_meta = effects.getItemMeta();
                            effect_meta.setDisplayName(ChatColor.RED + "Effects");
                            effects.setItemMeta(effect_meta);

                            ItemMeta none_meta = none.getItemMeta();
                            none_meta.setDisplayName(ChatColor.RED + " ");
                            none.setItemMeta(none_meta);

                            ItemStack[] menu_items = {eqipment, effects, none, none, none, none, none, none, none};
                            gui.setContents(menu_items);
                            player.openInventory(gui);
                            break;
                    }
            }

        }
        return false;
    }
}
