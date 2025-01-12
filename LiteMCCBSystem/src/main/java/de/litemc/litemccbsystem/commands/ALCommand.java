package de.litemc.litemccbsystem.commands;

import de.litemc.litemccbsystem.Main;
import de.litemc.litemccbsystem.Manager.CCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class ALCommand extends CCommand {
    public ALCommand() {
        super("adminlite", "Dies ist der LiteAdmin command für LiteMC.de", "/adminlite <option>", "al");
    }

    @Override
    public void run(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.ALCommand.Permission")))) {
                player.sendMessage(Main.getInstance().getPrefix() + "Dieser Command ist noch in der Entwicklung!");
                if (args[0].equalsIgnoreCase("reload")) {
                    Main.getInstance().reloadConfig();
                    player.sendMessage(Main.getInstance().getPrefix() + "Config wurde neu geladen!");
                } else if (args[0].equalsIgnoreCase("setlevel")) {
                    String playername = args[1];
                    Player target = Main.getInstance().getServer().getPlayer(playername);
                    if (target != null) {
                        Main.getInstance().getPlayerManager().setPlayerLevel(target, Integer.parseInt(args[2]));
                        Main.getInstance().getPlayerManager().savePlayerLevel(target);
                        player.sendMessage(Main.getInstance().getPrefix() + "Level von " + target.getName() + " wurde auf " + args[2] + " gesetzt!");
                    } else {
                        player.sendMessage(Main.getInstance().getPrefix() + "Dieser Spieler ist nicht online!");
                    }
                } else if (args[0].equalsIgnoreCase("warps")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        if (args[2].equalsIgnoreCase("FarmWorld")) {
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.X", player.getLocation().getX());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.Y", player.getLocation().getY());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.Z", player.getLocation().getZ());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.Yaw", player.getLocation().getYaw());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.Pitch", player.getLocation().getPitch());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.FarmWorld.World", Objects.requireNonNull(player.getLocation().getWorld()).getName());
                            Main.getInstance().saveConfig();
                            player.sendMessage(Main.getInstance().getPrefix() + "Warp FarmWorld wurde gesetzt!");
                        } else if (args[2].equalsIgnoreCase("Nether")) {
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.X", player.getLocation().getX());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.Y", player.getLocation().getY());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.Z", player.getLocation().getZ());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.Yaw", player.getLocation().getYaw());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.Pitch", player.getLocation().getPitch());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.Nether.World", Objects.requireNonNull(player.getLocation().getWorld()).getName());
                            Main.getInstance().saveConfig();
                            player.sendMessage(Main.getInstance().getPrefix() + "Warp Nether wurde gesetzt!");
                        } else if (args[2].equalsIgnoreCase("End")) {
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.X", player.getLocation().getX());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.Y", player.getLocation().getY());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.Z", player.getLocation().getZ());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.Yaw", player.getLocation().getYaw());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.Pitch", player.getLocation().getPitch());
                            Main.getInstance().getCustomConfig().set("Commands.WarpCommand.End.World", Objects.requireNonNull(player.getLocation().getWorld()).getName());
                            Main.getInstance().saveConfig();
                            player.sendMessage(Main.getInstance().getPrefix() + "Warp End wurde gesetzt!");
                        }
                    }
                } /*else if (args[0].equalsIgnoreCase("backtp")) {
                    if (args[1].equalsIgnoreCase("set")) {
                        if (args[3].equalsIgnoreCase("pos1") || args[3].equalsIgnoreCase("pos2") || args[3].equalsIgnoreCase("backpos") || args[3].equalsIgnoreCase("world")) {
                            if (args[3].equalsIgnoreCase("pos1")) {
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos1.X", player.getLocation().getX());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos1.Y", player.getLocation().getY());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos1.Z", player.getLocation().getZ());
                                Main.getInstance().saveConfig();
                                player.sendMessage(Main.getInstance().getPrefix() + "Pos1 wurde gesetzt!");
                            } else if (args[3].equalsIgnoreCase("pos2")) {
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos2.X", player.getLocation().getX());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos2.Y", player.getLocation().getY());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".pos2.Z", player.getLocation().getZ());
                                Main.getInstance().saveConfig();
                                player.sendMessage(Main.getInstance().getPrefix() + "Pos2 wurde gesetzt!");
                            } else if (args[3].equalsIgnoreCase("backpos")) {
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".backpos.X", player.getLocation().getX());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".backpos.Y", player.getLocation().getY());
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".backpos.Z", player.getLocation().getZ());
                                Main.getInstance().saveConfig();
                                player.sendMessage(Main.getInstance().getPrefix() + "BackPos wurde gesetzt!");
                            } else if (args[3].equalsIgnoreCase("world")) {
                                Main.getInstance().getCustomConfig().set("BackTP.Positions."+args[2]+".World", Objects.requireNonNull(player.getLocation().getWorld()).getName());
                                Main.getInstance().saveConfig();
                                player.sendMessage(Main.getInstance().getPrefix() + "World wurde gesetzt!");
                            }
                        }
                    } else if (args[1].equalsIgnoreCase("list")) {
                        player.sendMessage(Main.getInstance().getPrefix() + "BackTP Liste:");
                        player.sendMessage(Main.getInstance().getPrefix() + "World: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.World"));
                        player.sendMessage(Main.getInstance().getPrefix() + "X: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.X"));
                        player.sendMessage(Main.getInstance().getPrefix() + "Y: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.Y"));
                        player.sendMessage(Main.getInstance().getPrefix() + "Z: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.Z"));
                        player.sendMessage(Main.getInstance().getPrefix() + "Yaw: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.Yaw"));
                        player.sendMessage(Main.getInstance().getPrefix() + "Pitch: " + Main.getInstance().getCustomConfig().getString("Commands.ALCommand.BackTP.Pitch"));
                    }
                }*/
            } else {
                player.sendMessage(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.ALCommand.NoPermission")).replace("&", "§"));
            }
        }
    }

    public List<String> tabComplete(CommandSender sender, String label, String[] arguments) {
        List<String> arguments1 = Arrays.asList("reload", "setlevel", "warps");

        List<String> result = new ArrayList<String>();
        if (arguments.length == 1) {
            for (String s : arguments1) {
                if (s.toLowerCase().startsWith(arguments[0].toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }

        if (arguments.length == 2) {
            /*if (arguments[0].equalsIgnoreCase("backtp")) {
                List<String> arguments2 = Arrays.asList("set", "list");
                for (String s : arguments2) {
                    if (s.toLowerCase().startsWith(arguments[1].toLowerCase())) {
                        result.add(s);
                    }
                }
            }*/
            if (arguments[0].equalsIgnoreCase("setlevel")) {
                for (Player p : Main.getInstance().getServer().getOnlinePlayers()) {
                    if (p.getName().toLowerCase().startsWith(arguments[1].toLowerCase())) {
                        result.add(p.getName());
                    }
                }
            }
            if (arguments[0].equalsIgnoreCase("warps")) {
                List<String> arguments2 = Arrays.asList("set");
                for (String s : arguments2) {
                    if (s.toLowerCase().startsWith(arguments[1].toLowerCase())) {
                        result.add(s);
                    }
                }
            }
        }

        if (arguments.length == 3) {
            if (arguments[0].equalsIgnoreCase("warps")) {
                if (arguments[1].equalsIgnoreCase("set")) {
                    List<String> arguments3 = Arrays.asList("FarmWorld", "Nether", "End");
                    for (String s : arguments3) {
                        if (s.toLowerCase().startsWith(arguments[2].toLowerCase())) {
                            result.add(s);
                        }
                    }
                    return result;
                }
            }
        }

        /*if (arguments.length == 4) {
            if (arguments[0].equalsIgnoreCase("backtp")) {
                if (arguments[1].equalsIgnoreCase("set")) {
                    List<String> arguments3 = Arrays.asList("world", "pos1", "pos2", "posback");
                    for (String s : arguments3) {
                        if (s.toLowerCase().startsWith(arguments[3].toLowerCase())) {
                            result.add(s);
                        }
                    }
                    return result;
                }
            }
        }*/

        return result;
    }
}
