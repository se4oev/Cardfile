package ru.spb.reshenie.javatasks.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgDao implements IBaseDao {
    private Connection connection;
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String dbType = "jdbc:postgresql://";

    public Connection getConnection() {
        return connection;
    }

    public PgDao(String dbURL, String dbUser, String dbPassword) {
        String dbURL1 = dbType + dbURL;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbURL1, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
