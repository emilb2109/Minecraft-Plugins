package de.litemc.litemccbsystem.Manager;

import java.util.*;

import de.litemc.litemccbsystem.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerManager {

    private HashMap<UUID, Integer> playerLevel;

    public PlayerManager() {
        this.playerLevel = new HashMap<>();
    }

    public void addPlayer(Player player) {
        if (!isPlayer(player)) {
            YamlConfiguration config = Main.getInstance().getPlayerConfig();
            if (config.get("players." + player.getUniqueId()) != null) {
                this.playerLevel.put(player.getUniqueId(), config.getInt("players." + player.getUniqueId() + ".level"));
            } else {
                config.set("players." + player.getUniqueId() + ".level", 0);
                Main.getInstance().savePlayerFile();
                this.playerLevel.put(player.getUniqueId(), 0);
            }
        }
    }

    public void setPlayerLevel(Player player, int level) {
        if (isPlayer(player)) {
            this.playerLevel.put(player.getUniqueId(), level);
        }
    }

    public int getPlayerLevel(Player player) {
        if (isPlayer(player)) {
            return this.playerLevel.get(player.getUniqueId());
        }
        return 0;
    }

    public void savePlayerLevel(Player player) {
        if (isPlayer(player)) {
            YamlConfiguration config = Main.getInstance().getPlayerConfig();
            config.set("players." + player.getUniqueId() + ".level", this.playerLevel.get(player.getUniqueId()));
            Main.getInstance().savePlayerFile();
        }
    }

    public boolean isPlayer(Player player) {
        return this.playerLevel.containsKey(player.getUniqueId());
    }

}
