package de.emilb2109.casesystem.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.emilb2109.casesystem.Main;
import jdk.internal.logger.SurrogateLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseManager {

    private final HikariDataSource hikariDataSource;

    public DatabaseManager() {
        String host = Main.getInstance().getConfig().getString("mysql.host", "localhost");
        int port = Main.getInstance().getConfig().getInt("mysql.port", 3306);
        String user = Main.getInstance().getConfig().getString("mysql.user", "user");
        String password = Main.getInstance().getConfig().getString("mysql.password", "password");
        String database = Main.getInstance().getConfig().getString("mysql.database", "database");

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);

        hikariConfig.setConnectionTimeout(3000);
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");

        String jdbcString = "jdbc:mysql://" + host + ":" + port + "/" + database;
        hikariConfig.setJdbcUrl(jdbcString);

        hikariDataSource = new HikariDataSource(hikariConfig);

        try {
            Connection connection = hikariDataSource.getConnection();
            closeConnection(connection);
            Logger.getLogger("com.zaxxer.hikari.pool.PoolBase").setLevel(Level.OFF);
            Logger.getLogger("com.zaxxer.hikari.pool.HikariPool").setLevel(Level.OFF);
            Logger.getLogger("com.zaxxer.hikari.HikariDataSource").setLevel(Level.OFF);
            Logger.getLogger("com.zaxxer.hikari.HikariConfig").setLevel(Level.OFF);
            Logger.getLogger("com.zaxxer.hikari.util.DriverDataSource").setLevel(Level.OFF);
        } catch (SQLException e) {
            System.out.println("Fehler bei der Datenbank-Verbindung");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) {
        hikariDataSource.evictConnection(connection);
    }

    public void shutDownDatasource() {
        hikariDataSource.close();
    }
}
