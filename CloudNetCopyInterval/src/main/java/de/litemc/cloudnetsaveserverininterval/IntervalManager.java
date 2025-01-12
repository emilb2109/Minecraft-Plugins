package de.litemc.cloudnetsaveserverininterval;

import de.dytanic.cloudnet.CloudNet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class IntervalManager {

    private void run() {
        while (true) {
            try {
                Thread.sleep(Main.getInstance().getConfig().getInt("interval") * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            CloudNet.getInstance().getLogger().info("Saving server...");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cloudnet cp " + Main.getInstance().getConfig().getString("serverName"));
            CloudNet.getInstance().getLogger().info("Saved server!");
            Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Saved server!");
        }
    }

    public void start() {
        Thread thread = new Thread(this::run);
        thread.start();
    }

}
