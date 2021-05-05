package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.entity.Patient;

import java.sql.ResultSet;
import java.util.List;

public interface IBaseDao {
    List<Patient> getAll();
}
