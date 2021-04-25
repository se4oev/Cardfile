package ru.spb.reshenie.javatasks.entity;

import java.util.Date;

public class Patient {
    private long id;
    private String fio;
    private Date birth_date;
    private int sex;
    private int num;
    private String smo;
    private String snils;
    private String policy;
    private int fin_source;

    public Patient(long id, String fio, Date birth_date, int sex, int num,
                   String smo, String snils, String policy, int fin_source) {
        this.id = id;
        this.fio = fio;
        this.birth_date = birth_date;
        this.sex = sex;
        this.num = num;
        this.smo = smo;
        this.snils = snils;
        this.policy = policy;
        this.fin_source = fin_source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSmo() {
        return smo;
    }

    public void setSmo(String smo) {
        this.smo = smo;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public int getFin_source() {
        return fin_source;
    }

    public void setFin_source(int fin_source) {
        this.fin_source = fin_source;
    }
}
