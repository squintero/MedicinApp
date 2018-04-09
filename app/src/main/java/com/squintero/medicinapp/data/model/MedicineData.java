package com.squintero.medicinapp.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "medicines")
public class MedicineData {
    @PrimaryKey
    private int id              = -1;

    @ColumnInfo(name = "name")
    private String name         = "";

    @ColumnInfo(name = "extra_info")
    private String extraInfo    = "";

    public MedicineData() {
    }

    public MedicineData(int id, String name, String surname, int age, String extraInfo) {
        this.id = id;
        this.name = name;
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

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
