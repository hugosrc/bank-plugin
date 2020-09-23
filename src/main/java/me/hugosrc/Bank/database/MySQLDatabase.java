package me.hugosrc.Bank.database;

import lombok.Builder;
import me.hugosrc.Bank.BankPlugin;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabase {

    private final Credentials credentials;

    private Connection connection;
    private boolean active;

    public MySQLDatabase(Credentials credentials) {
        this.credentials = credentials;
    }

    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://" + credentials.ip + ":" + credentials.port + "/" + credentials.database;

            this.connection = DriverManager.getConnection(url, credentials.user, credentials.password);

            DatabaseMetaData metaData = connection.getMetaData();
            BankPlugin.logger.info("Connected to the database" + metaData.getDatabaseProductName());
            BankPlugin.logger.info("Driver: " + metaData.getDriverName());

            this.active = true;
        } catch (Exception e) {
            BankPlugin.logger.severe(e.getLocalizedMessage());
            BankPlugin.logger.severe(e.getMessage());
        }
    }

    public Connection getConnection() {
        if (connection == null || !active) this.connect();

        return connection;
    }

    public void disconnect() {
        if (connection != null && active) {
            try {
                connection.close();
                active = false;

                BankPlugin.logger.info("Disconnected to the database");
            } catch (SQLException e) {
                BankPlugin.logger.severe(e.getLocalizedMessage());
                BankPlugin.logger.severe(e.getMessage());
            }
        }
    }

    @Builder
    public static class Credentials {
        private String ip;
        private int port;
        private String user, password, database;
    }

}
