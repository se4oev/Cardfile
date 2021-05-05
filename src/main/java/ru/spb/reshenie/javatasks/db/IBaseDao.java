package ru.spb.reshenie.javatasks.db;

import java.sql.Connection;

public interface IBaseDao {
    Connection getConnection();
}
