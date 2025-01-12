package de.litemc.litemccbsystem.commands;

import de.litemc.litemccbsystem.Main;
import de.litemc.litemccbsystem.Manager.CCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HelpCommand extends CCommand {
    public HelpCommand() {
        super(""+ Main.getInstance().getCustomConfig().getString("Commands.HelpCommand.Command"), "Dies ist der Help command für LiteMC.de", "/hilfe");
    }

    @Override
    public void run(CommandSender cs, String commandLabel, String[] args) {
        if(cs instanceof Player) {
            Player player = (Player) cs;
            if(cs.hasPermission(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.HelpCommand.Permission")))) {
                for (String s : Main.getInstance().getCustomConfig().getStringList("Commands.HelpCommand.Text")) {
                    player.sendMessage(s.replace("&", "§"));
                }
            } else {
                player.sendMessage(Main.getInstance().getCustomConfig().getString("Commands.HelpCommand.NoPermission").replace("&", "§"));
            }
        }
    }
}
