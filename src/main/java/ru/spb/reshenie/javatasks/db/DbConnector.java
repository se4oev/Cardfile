package ru.spb.reshenie.javatasks.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static String dbType = "jdbc:postgresql://";
    private String dbURL;
    private String dbUser;
    private String dbPassword;

    public DbConnector(String[] dbURL, String dbUser, String dbPassword) {
        this.dbURL = dbType + dbURL[1];
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}
