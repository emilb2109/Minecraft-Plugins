package de.emilb2109.casesystem.commands;

import de.emilb2109.casesystem.Main;
import de.emilb2109.casesystem.manager.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class Case implements CommandExecutor {
    /**
     * /case set <player> <case> <nummer>
     * /case help
     * /case setweiter
     * /case setcase
     * /case setzurueck
     * /case setspawn
     **/
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("set")) {
                if (cs.hasPermission("system.case.set") || cs.hasPermission("system.case.*")) {
                    String playerName = args[1];
                    Player targetPlayer = Bukkit.getPlayer(playerName);
                    if(targetPlayer != null) {
                        PlayerStats playerStats = Main.getInstance().getStatsManager().getPlayerStats(targetPlayer.getUniqueId());
                        String numberString = args[3];
                        int number = Integer.parseInt(numberString);
                        if(args[2].equalsIgnoreCase("vote")) {
                            playerStats.setVote(number);
                            Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                            cs.sendMessage(Main.getInstance().getPrefix()+ " Dem Spieler wurde die Case erfolgreich hinzugefügt.");
                        }
                        if(args[2].equalsIgnoreCase("supreme")) {
                            playerStats.setSupreme(number);
                            Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                            cs.sendMessage(Main.getInstance().getPrefix()+ " Dem Spieler wurde die Case erfolgreich hinzugefügt.");
                        }
                        if(args[2].equalsIgnoreCase("mega")) {
                            playerStats.setMega(number);
                            Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                            cs.sendMessage(Main.getInstance().getPrefix()+ " Dem Spieler wurde die Case erfolgreich hinzugefügt.");
                        }
                        else {
                            cs.sendMessage(Main.getInstance().getPrefix()+ " Diese Case gibt es nicht, /case help für Hilfe.");
                        }

                    }
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
        } else if (args.length == 1) {
            if(args[0].equalsIgnoreCase("help")) {
                if(cs.hasPermission("system.case.help") || cs.hasPermission("system.case.*")) {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Hilfe:\n" + "§7/case set <player> <vote/supreme/mega> <number>\n" + "§7/case setcase\n" + "§7/case setweiter\n" + "§7/case setzurueck\n" + "§7/case setspawn");
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
            if(args[0].equalsIgnoreCase("setcase")) {
                if(cs.hasPermission("system.case.setcase") || cs.hasPermission("system.case.*")) {
                    Player p = (Player) cs;
                    String loc = Main.getInstance().getMethod().saveLocationToString(p.getLocation());
                    Main.getInstance().getConfig().set("case.open.case", loc);
                    p.sendMessage(Main.getInstance().getPrefix() + " Erfolgreich hinzugefügt.");
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
            if(args[0].equalsIgnoreCase("setweiter")) {
                if(cs.hasPermission("system.case.setweiter") || cs.hasPermission("system.case.*")) {
                    Player p = (Player) cs;
                    String loc = Main.getInstance().getMethod().saveLocationToString(p.getLocation());
                    Main.getInstance().getConfig().set("case.open.weiter", loc);
                    p.sendMessage(Main.getInstance().getPrefix() + " Erfolgreich hinzugefügt.");
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
            if(args[0].equalsIgnoreCase("setzurueck")) {
                if(cs.hasPermission("system.case.setzurueck") || cs.hasPermission("system.case.*")) {
                    Player p = (Player) cs;
                    String loc = Main.getInstance().getMethod().saveLocationToString(p.getLocation());
                    Main.getInstance().getConfig().set("case.open.zurueck", loc);
                    p.sendMessage(Main.getInstance().getPrefix() + " Erfolgreich hinzugefügt.");
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                if(cs.hasPermission("system.case.setspawn") || cs.hasPermission("system.case.*")) {
                    Player p = (Player) cs;
                    String loc = Main.getInstance().getMethod().saveLocationToString(p.getLocation());
                    Main.getInstance().getConfig().set("case.open.spawn", loc);
                    p.sendMessage(Main.getInstance().getPrefix() + " Erfolgreich hinzugefügt.");
                }
                else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
                }
            }
        }
        else if (args.length == 0) {
            if(cs.hasPermission("system.case.help") || cs.hasPermission("system.case.*")) {
                cs.sendMessage(Main.getInstance().getPrefix() + " §e/case help§7 für Hilfe.");
            }
            else {
                cs.sendMessage(Main.getInstance().getPrefix() + " Dafür hast du keine Rechte.");
            }
        }
        return false;
    }
}
