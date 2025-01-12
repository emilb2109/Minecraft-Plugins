package de.emilb2109.supportsystem.commands;

import de.emilb2109.supportsystem.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ticket implements CommandExecutor {
    List<String> ticket = new ArrayList<>();

    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(Main.getInstance().getConfig().getBoolean("enable")) {
            if(args.length == 0) {
                cs.sendMessage(Main.getInstance().getPrefix() + " Syntax: §c/ticket <MESSAGE>§7");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("close")) {
                    if(ticket.contains(((Player) cs).getDisplayName())) {
                        cs.sendMessage(Main.getInstance().getPrefix() + " The Ticket has been Closed.");
                        Main.getInstance().getMethod().logMessage("Ticket Close", "The Player " + ((Player) cs).getDisplayName() + " has been Closed a Ticket.");
                        ticket.remove(((Player) cs).getDisplayName());
                    } else {
                        cs.sendMessage(Main.getInstance().getPrefix() + " You don't have a Ticket open.");
                    }
                } else if (cs instanceof Player) {
                    if (ticket.contains(((Player) cs).getDisplayName())) {
                        cs.sendMessage(Main.getInstance().getPrefix() +  " You have already a Ticket open, pleas close you Ticket first. §c/ticket close§7");
                    } else {
                        String message = "";
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        message = sb.toString().trim();
                        Main.getInstance().getMethod().discordMessage(((Player) cs).getDisplayName(), message);
                        Main.getInstance().getMethod().logMessage("Ticket Open", "The Player " + ((Player) cs).getDisplayName() + " has Open a Ticket. Text: " + message);
                        cs.sendMessage(Main.getInstance().getPrefix() + " Ticket has been Created.");
                        ticket.add(((Player) cs).getDisplayName());
                    }
                } else {
                    cs.sendMessage("You are not a Player.");
                }
            } else {
                if (cs instanceof Player) {
                    if (ticket.contains(((Player) cs).getDisplayName())) {
                        cs.sendMessage(Main.getInstance().getPrefix() +  " You have already a Ticket open, pleas close you Ticket first. §c/ticket close§7");
                    } else {
                        String message = "";
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < args.length; i++) {
                            sb.append(args[i]).append(" ");
                        }
                        message = sb.toString().trim();
                        Main.getInstance().getMethod().discordMessage(((Player) cs).getDisplayName(), message);
                        Main.getInstance().getMethod().logMessage("Ticket Open", "The Player " + ((Player) cs).getDisplayName() + " has Open a Ticket. Text: " + message);
                        cs.sendMessage(Main.getInstance().getPrefix() + " Ticket has been Created.");
                        ticket.add(((Player) cs).getDisplayName());
                    }
                } else {
                    cs.sendMessage("You are not a Player.");
                }
            }
        } else {
            cs.sendMessage(Main.getInstance().getPrefix() + " This Command is Deactivate.");
        }
        return true;
    }
}
