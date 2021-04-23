package ru.spb.reshenie.javatasks.entity;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.time.LocalDate;

public class Patient {
    private final IntegerProperty cardNumber;
    private final StringProperty snils;
    private final StringProperty sex;
    private final StringProperty fullname;
    private final StringProperty birthday;
    private final StringProperty age;
    private final StringProperty policy;
    private final ObjectProperty<Image> finSource;

    public Patient(Integer cardNumber, String snils, String sex, String fullname, String birthday, String age, String policy, Image finSource) {
        this.cardNumber = new SimpleIntegerProperty(cardNumber);
        this.snils = new SimpleStringProperty(snils);
        this.sex = new SimpleStringProperty(sex);
        this.fullname = new SimpleStringProperty(fullname);
        this.birthday = new SimpleStringProperty(birthday);
        this.age = new SimpleStringProperty(age);
        this.policy = new SimpleStringProperty(policy);
        this.finSource = new SimpleObjectProperty<Image>(finSource);
    }

    public int getCardNumber() {
        return cardNumber.get();
    }

    public IntegerProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber.set(cardNumber);
    }

    public String getSnils() {
        return snils.get();
    }

    public StringProperty snilsProperty() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils.set(snils);
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public String getFullname() {
        return fullname.get();
    }

    public StringProperty fullnameProperty() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname.set(fullname);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public StringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getAge() {
        return age.get();
    }

    public StringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getPolicy() {
        return policy.get();
    }

    public StringProperty policyProperty() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy.set(policy);
    }

    public Image getFinSource() {
        return finSource.get();
    }

    public ObjectProperty<Image> finSourceProperty() {
        return finSource;
    }

    public void setFinSource(Image finSource) {
        this.finSource.set(finSource);
    }
}
