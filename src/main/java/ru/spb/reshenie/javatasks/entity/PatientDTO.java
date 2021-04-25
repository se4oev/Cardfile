package ru.spb.reshenie.javatasks.entity;

import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PatientDTO {
    private StringProperty cardNumber;
    private StringProperty snils;
    private StringProperty sex;
    private StringProperty fullname;
    private StringProperty birthday;
    private StringProperty age;
    private StringProperty policy;
    private ObjectProperty<ImageView> finSource;

    public PatientDTO() {

    }

    public PatientDTO(Long cardNumber, String snils, String sex, String fullname, String birthday, String age, String policy, ImageView finSource) {
        this.cardNumber = new SimpleStringProperty(cardNumber.toString());
        this.snils = new SimpleStringProperty(snils);
        this.sex = new SimpleStringProperty(sex);
        this.fullname = new SimpleStringProperty(fullname);
        this.birthday = new SimpleStringProperty(birthday);
        this.age = new SimpleStringProperty(age);
        this.policy = new SimpleStringProperty(policy);
        this.finSource = new SimpleObjectProperty<ImageView>(finSource);
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
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

    public ImageView getFinSource() {
        return finSource.get();
    }

    public ObjectProperty<ImageView> finSourceProperty() {
        return finSource;
    }

    public void setFinSource(Image finSource) {
        this.finSource.set(new ImageView(finSource));
    }
}
