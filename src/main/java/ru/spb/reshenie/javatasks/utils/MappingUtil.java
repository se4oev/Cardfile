package ru.spb.reshenie.javatasks.utils;

import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.entity.PatientDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MappingUtil {

    public static List<PatientDTO> mapToPatientDTOList(List<Patient> patients) {
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient entry : patients) {
            patientDTOList.add(mapToPatientDTO(entry));
        }
        return patientDTOList;
    }

    public static PatientDTO mapToPatientDTO(Patient entity) {

        long cardNumber = entity.getNum();
        String snils = snilsFormat(entity.getSnils());
        String sex = sexFormat(entity.getSex());
        String fullname = entity.getFio();
        String birthday = birthdayFormat(entity.getBirth_date());
        String age = ageFormat(entity.getBirth_date());
        String policy = policyFormat(entity.getSmo(), entity.getPolicy());
        Integer finSource = entity.getFin_source();

        return new PatientDTO(cardNumber, snils, sex, fullname, birthday, age, policy, finSource);
    }

    private static String policyFormat(String smo, String policy) {
        return smo + " - " + policy;
    }

    private static String ageFormat(Date birth_date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = Instant.ofEpochMilli(birth_date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        int years = 0;
        if (birthDate != null) {
            years = Period.between(birthDate, currentDate).getYears();
        }

        return appendYearsName(years);
    }

    private static String appendYearsName(int years) {
        String output = "";
        if (years >= 10 && years <= 20) {
            output = String.valueOf(years) + " лет";
        } else if (years % 10 == 0 || years % 10 == 5 || years % 10 == 6 || years % 10 == 7 ||
                years % 10 == 8 || years % 10 == 9) {
            output = String.valueOf(years) + " лет";
        } else if (years % 10 == 1) {
            output = String.valueOf(years) + " год";
        } else if (years % 10 == 2 || years % 10 == 3 || years % 10 == 4) {
            output = String.valueOf(years) + " года";
        }
        return output;
    }

    private static String birthdayFormat(Date birth_date) {
        Date simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(birth_date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).format(simpleDateFormat);
    }

    private static String sexFormat(Integer sex) {
        if (sex == 1 || sex == 2) {
            return sex == 1 ? "Мужской" : "Женский";
        } else {
            return null;
        }
    }

    private static String snilsFormat(String snils) {
        String result = "";
        for (int i = 0; i < snils.length(); i++) {
            result += snils.charAt(i);
            if ((i + 1) % 3 == 0) {
                result += "-";
            }
        }
        return result;
    }


}
