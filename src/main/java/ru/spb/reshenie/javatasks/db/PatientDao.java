package ru.spb.reshenie.javatasks.db;

import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.utils.PatientMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDao {
    private final IBaseDao baseDao;

    public PatientDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public List<Patient> getAll() {
        List<Patient> listOfPatient = new ArrayList<>();
        ResultSet resultSet;
        try (Statement statement = baseDao.getConnection().createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM java_tasks_patient ORDER BY id DESC");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String fio = resultSet.getString("fio");

                String[] birthDay = resultSet.getDate("birth_date").toString().split("-");
                LocalDate birthDate = LocalDate.of(Integer.parseInt(birthDay[0]), Integer.parseInt(birthDay[1]), Integer.parseInt(birthDay[2]));

                int sex = resultSet.getInt("sex");
                int num = resultSet.getInt("num");
                String smo = resultSet.getString("smo");
                String snils = resultSet.getString("snils");
                String policy = resultSet.getString("policy");
                int fin_source = resultSet.getInt("fin_source");
                PatientMapper mapper = new PatientMapper(id, fio, birthDate, sex, num, smo, snils, policy, fin_source);

                listOfPatient.add(mapper.getPatient());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfPatient;
    }

}
