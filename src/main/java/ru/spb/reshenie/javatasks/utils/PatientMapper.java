package ru.spb.reshenie.javatasks.utils;

import ru.spb.reshenie.javatasks.entity.Patient;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PatientMapper {
    private final Patient patient;

    public PatientMapper(long id, String fio, LocalDate birthDate, int sex, int num,
                         String smo, String snils, String policy, int finSource) {
        String snilsFormat = snilsFormat(snils);
        String sexString = sexFormat(sex);
        String birthday = birthdayFormat(birthDate);
        String age = ageFormat(birthDate);
        String policyFormat = policyFormat(smo, policy);
        patient = new Patient(
            id, num, snilsFormat, sexString, fio,
            birthday, age, policyFormat, finSource
        );
    }

    public Patient getPatient() {
        return patient;
    }

    private static String policyFormat(String smo, String policy) {
        return smo + " - " + policy;
    }

    private String snilsFormat(String snils) {
        StringBuilder stringBuilder = new StringBuilder(snils);
        stringBuilder.insert(3, "-");
        stringBuilder.insert(7, "-");
        stringBuilder.insert(11, "-");
        return stringBuilder.toString();
    }

    private String sexFormat(Integer sex) {
        if (sex == 1 || sex == 2) {
            return sex == 1 ? "Мужской" : "Женский";
        } else {
            return "Не указан";
        }
    }

    private String birthdayFormat(LocalDate birthDate) {
        String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return birthDate.format(formatter);
    }

    private String ageFormat(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        int years = 0;
        if (birthDate != null) {
            years = Period.between(birthDate, currentDate).getYears();
        }

        return appendYearsName(years);
    }

    private String appendYearsName(int years) {
        String output = "";
        if (years >= 10 && years <= 20) {
            output = years + " лет";
        } else if (years % 10 == 0 || years % 10 == 5 || years % 10 == 6 || years % 10 == 7 ||
                years % 10 == 8 || years % 10 == 9) {
            output = years + " лет";
        } else if (years % 10 == 1) {
            output = years + " год";
        } else if (years % 10 == 2 || years % 10 == 3 || years % 10 == 4) {
            output = years + " года";
        }
        return output;
    }
}
