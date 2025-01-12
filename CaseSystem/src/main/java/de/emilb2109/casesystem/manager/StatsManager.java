package de.emilb2109.casesystem.manager;

import de.emilb2109.casesystem.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class StatsManager {

    private final HashMap<UUID, PlayerStats> cachedPlayerStatsMap;

    public StatsManager() {

        cachedPlayerStatsMap = new HashMap<>();

        try {
            Connection connection = Main.getInstance().getDatabaseManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `playerCases` ( `uuid` VARCHAR(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL, `vote` INT NOT NULL DEFAULT '0', `supreme` INT NOT NULL DEFAULT '0', `mega` INT NOT NULL DEFAULT '0', PRIMARY KEY (`uuid`) );");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Main.getInstance().getDatabaseManager().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void loadPlayerStats(UUID uuid) {
        PlayerStats playerStats = getPlayerStats(uuid);
        if(playerStats == null) {
            playerStats = createPlayerStats(uuid);
        }
        cachedPlayerStatsMap.put(uuid, playerStats);
    }

    public void unloadPlayerStats(UUID uuid) {
        savePlayerStats(getPlayerStats(uuid), true);
    }

    public void savePlayerStats(PlayerStats playerStats, boolean removeFromCach) {
        updatePlayerStats(playerStats);
        if(removeFromCach) {
            cachedPlayerStatsMap.remove(playerStats.getUuid());
        }
    }

    public PlayerStats createPlayerStats(UUID uuid) {
        PlayerStats playerStats = new PlayerStats(uuid);

        try {
            Connection connection = Main.getInstance().getDatabaseManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO `playerCases` (`uuid`) VALUES ('" + playerStats.getUuid().toString() + "')");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Main.getInstance().getDatabaseManager().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerStats;

    }

    public PlayerStats getPlayerStats(UUID uuid) {
        if(cachedPlayerStatsMap.containsKey(uuid)) {
            return cachedPlayerStatsMap.get(uuid);
        }
        PlayerStats playerStats = null;
        try {
            Connection connection = Main.getInstance().getDatabaseManager().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM `playerCases` WHERE `uuid` = '" + uuid.toString() + "'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                playerStats = new PlayerStats(uuid, resultSet.getInt("vote"), resultSet.getInt("supreme"), resultSet.getInt("mega"));
            }
            resultSet.close();
            preparedStatement.close();
            Main.getInstance().getDatabaseManager().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(playerStats != null) {
            cachedPlayerStatsMap.put(uuid, playerStats);
        }

        return playerStats;
    }


    public void updatePlayerStats(PlayerStats playerStats) {
        try {
            Connection connection = Main.getInstance().getDatabaseManager().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE `playerCases` SET `vote` = '" + playerStats.getVote() + "', `supreme` = '" + playerStats.getSupreme() + "', `mega` = '" + playerStats.getMega() + "' WHERE `uuid` = '" + playerStats.getUuid().toString() +"'");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            Main.getInstance().getDatabaseManager().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
