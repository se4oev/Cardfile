package ru.spb.reshenie.javatasks.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDao implements IBaseDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Connection connection;
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String dbType = "jdbc:postgresql://";

    public Connection getConnection() {
        return connection;
    }

    public PgDao(String dbURL, String dbUser, String dbPassword) {
        String dbURL1 = dbType + dbURL;
        try {
            logger.info("Try to connect DB...");
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbURL1, dbUser, dbPassword);
            logger.info("Connect successful");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to connect DB", e);
        }
    }
}
