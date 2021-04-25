package ru.spb.reshenie.javatasks.db;

import java.util.List;

public interface BaseDao<T> {

    //read
    List<T> getAll();

}
