package de.emilb2109.lobbysystem.Commands;

import de.emilb2109.lobbysystem.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BuildCommand implements CommandExecutor {
    List<String> buildmodetoggle = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cs instanceof Player) {
            Player player = (Player) cs;
            if(cs.hasPermission("lobby.build")) {
                if(!buildmodetoggle.contains(player.getName())) {
                    cs.sendMessage(Main.getInstance().getPrefix() + " §7Sie sind jetzt im §eBuildmodus §7und können nun Bauen.");
                    Main.getInstance().getPlayerManager().addPlayer(player.getPlayer());
                    buildmodetoggle.add(player.getName());
                } else {
                    cs.sendMessage(Main.getInstance().getPrefix() + " §7Sie sind jetzt nicht mehr im §cBuildmodus §7und können nun nicht mehr Bauen.");
                    Main.getInstance().getPlayerManager().removePlayer(player.getPlayer());
                    buildmodetoggle.remove(player.getName());
                }
            }
        }
        return true;
    }
}
