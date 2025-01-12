package de.emilb2109.lobbysystem;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {

    private List<Player> players;

    public PlayerManager()  {
        this.players = Lists.newArrayList();
    }

    public void addPlayer(Player player) {
        if (!isPlayer(player)) {
            this.players.add(player);
        }
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public boolean isPlayer(Player player) {
        return this.players.contains(player);
    }
}
