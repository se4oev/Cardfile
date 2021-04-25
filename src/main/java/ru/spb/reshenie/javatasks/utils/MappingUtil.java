package ru.spb.reshenie.javatasks.utils;

import javafx.scene.image.Image;
import ru.spb.reshenie.javatasks.entity.Patient;
import ru.spb.reshenie.javatasks.entity.PatientDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String fullname = fullnameFormat(entity.getFio());
        String birthday = birthdayFormat(entity.getBirth_date());
        String age = ageFormat(entity.getBirth_date());
        String policy = policyFormat(entity.getSmo(), entity.getPolicy());
        Image finSource = finSourceFormat(entity.getFin_source());

//        dto.setCardNumber(String.valueOf(entity.getNum()));
//        dto.setSnils(snilsFormat(entity.getSnils()));
//        dto.setSex(sexFormat(entity.getSex()));
//        dto.setFullname(fullnameFormat(entity.getFio()));
//        dto.setBirthday(birthdayFormat(entity.getBirth_date()));
//        dto.setAge(ageFormat(entity.getBirth_date()));
//        dto.setPolicy(policyFormat(entity.getSmo(), entity.getPolicy()));
//        dto.setFinSource(finSourceFormat(entity.getFin_source()));

        return new PatientDTO(cardNumber, snils, sex, fullname, birthday, age, policy, finSource);
    }

    private static Image finSourceFormat(Integer fin_source) {
        if (fin_source == 1) {
            return ImageUtil.omsImage;
        }
        if (fin_source == 2) {
            return ImageUtil.dmsImage;
        }
        if (fin_source == 3) {
            return ImageUtil.cashImage;
        }
        return null;
    }

    private static String policyFormat(String smo, String policy) {
        return smo + " - " + policy;
    }

    private static String ageFormat(Date birth_date) {
        Date date = new Date();
        return new Date(date.getTime() - birth_date.getTime()).toString();
    }

    private static String birthdayFormat(Date birth_date) {
        return birth_date.toString();
    }

    private static String fullnameFormat(String fio) {
        String[] fullname = fio.split(" ");
        String result = fullname[0];
        if (fullname.length >= 3) {
            result += " " + fullname[1].charAt(0) + ". " + fullname[2].charAt(0) + "."; 
        } else if (fullname.length == 2) {
            result += " " + fullname[1].charAt(0) + ".";
        }
        return result;
        
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
