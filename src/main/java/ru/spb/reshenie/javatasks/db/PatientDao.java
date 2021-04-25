package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.db.BaseDao;
import ru.spb.reshenie.javatasks.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements BaseDao<Patient> {
    private Connection connection;

    public PatientDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> listOfPatient = new ArrayList<Patient>();
        ResultSet resultSet = null;
        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM java_tasks_patient");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fio = resultSet.getString("fio");
                Date birth_date = resultSet.getDate("birth_date");
                int sex = resultSet.getInt("sex");
                int num = resultSet.getInt("num");
                String smo = resultSet.getString("smo");
                String snils = resultSet.getString("snils");
                String policy = resultSet.getString("policy");
                int fin_source = resultSet.getInt("fin_source");
                Patient patient = new Patient(id, fio, birth_date, sex, num, smo,
                        snils, policy, fin_source);
                listOfPatient.add(patient);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfPatient;
    }

}
