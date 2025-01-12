package de.litemc.litemccbsystem.commands;

import de.litemc.litemccbsystem.Main;
import de.litemc.litemccbsystem.Manager.CCommand;
import de.litemc.litemccbsystem.Manager.Menu.Menus.WarpCommandMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends CCommand {
    public WarpCommand() {
        super("warp", "Warp to a location", "/warp", "warps");
    }

    @Override
    public void run(CommandSender sender, String commandLabel, String[] arguments) {
        if (sender instanceof Player) {

            Player p = (Player) sender;
            WarpCommandMenu menu = new WarpCommandMenu(Main.getPlayerMenuUtility((p)));
            menu.open();
        }
    }
}
