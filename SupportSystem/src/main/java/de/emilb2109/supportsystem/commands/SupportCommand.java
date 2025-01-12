package de.emilb2109.supportsystem.commands;

import de.emilb2109.supportsystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

public class SupportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (cs instanceof Player) {
            if (args.length == 0) {
                cs.sendMessage(Main.getInstance().getPrefix() + " For Help §c/support help§7.");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Help:\n§c/support tp <TICKET PLAYER/ID>\n§c/support chat <TICKET PLAYER/ID>");
                }
            } else if (args.length == 2) {
                String target = args[1];
                Player sender = ((Player) cs);
                Player targetPlayer = Bukkit.getPlayer(target);
                if (args[0].equalsIgnoreCase("tp")) {
                    if(Bukkit.getOnlinePlayers().contains(targetPlayer)) {
                        if (targetPlayer != null) {
                            sender.teleport(targetPlayer.getLocation());
                            Main.getInstance().getMethod().logMessage("Support Command", "`/support tp " + targetPlayer.getDisplayName() + "` was executed by **" + ((Player) cs).getDisplayName() + "**");
                        }
                    }
                } if (args[1].equalsIgnoreCase("chat")) {
                    if (targetPlayer != null) {

                    }
                }
            } else  {cs.sendMessage(Main.getInstance().getPrefix() + " For Help §c/support help§7.");}
        }
        return true;
    }
}
