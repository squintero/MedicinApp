package com.squintero.medicinapp.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "persons")
public class PersonData {
    @PrimaryKey
    private int id              = -1;

    @ColumnInfo(name = "name")
    private String name         = "";

    @ColumnInfo(name = "surname")
    private String surname      = "";

    @ColumnInfo(name = "age")
    private int age             = -1;

    @ColumnInfo(name = "extra_info")
    private String extraInfo    = "";

    //TODO assigned medicines ??

    public PersonData() {
    }

    public PersonData(int id, String name, String surname, int age, String extraInfo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.extraInfo = extraInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
