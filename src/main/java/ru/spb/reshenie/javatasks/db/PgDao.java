package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.entity.Patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PgDao implements IBaseDao {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String dbType = "jdbc:postgresql://";
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection connection;

    public PgDao(String dbURL, String dbUser, String dbPassword) {
        this.dbURL = dbURL;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getAll() {
        return null;
    }
}
