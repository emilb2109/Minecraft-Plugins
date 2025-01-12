package de.emilb2109.adminmanager.commands;

import de.emilb2109.adminmanager.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;

import java.util.ArrayList;
import java.util.List;


public class adutyCommand implements CommandExecutor {
    List<String> adutytog = new ArrayList<>();

    @Override

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("system.team.aduty.command")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
                return false;
            }
            Player player = (Player) sender;
            if (adutytog.contains(player.getName())) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission set system.team.admin.command false");
                sender.sendMessage(ChatColor.RED + "Aduty Deactivate");
                Bukkit.getConsoleSender().sendMessage("Aduty off for " + sender.getName());
                adutytog.remove(player.getName());
                return true;
            }
            if (!(adutytog.contains(player.getName()))) {
                sender.sendMessage(ChatColor.RED + "Aduty Active. " + ChatColor.GOLD + "/admin help " + ChatColor.RED + "for more.");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + sender.getName() + " permission set system.team.admin.command true");
                Bukkit.getConsoleSender().sendMessage("Aduty on for " + sender.getName());
                adutytog.add(player.getName());
                return true;
            }
        }
        //if (adutyoo.getConfig().getBoolean("commands.aduty.enable")) {
        //    sender.sendMessage("Test1");
        //}
        //else {
        //    sender.sendMessage("Test2");
        //s}
        return false;
    }
}
