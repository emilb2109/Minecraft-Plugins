package de.emilb2109.todolist.Commands;

import de.emilb2109.todolist.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class ToDoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
        if(cs instanceof Player) {
            if (cs.hasPermission("system.todolist")) {
                    if (args.length == 2) {
                        if (args[0].equalsIgnoreCase("add")) {
                            Main.getInstance().getToDo().set("todo.messages", args[1]);
                        }
                    } else if (args[0].equalsIgnoreCase("list")) {
                        cs.sendMessage("ToDo List:\n");
                        List<String> messagesList = (List<String>) Main.getInstance().getToDo().get("todo.messages");
                        for (int i = 0; i < messagesList.size(); i++) {
                            String message = messagesList.get(1);
                            messagesList.remove(message);
                            cs.sendMessage("" + message + "\n");
                        }
                    }

            }
        }
        return true;
    }
}
