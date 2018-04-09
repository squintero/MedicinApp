package com.squintero.medicinapp.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.squintero.medicinapp.data.model.MedicineData;
import com.squintero.medicinapp.data.model.PersonData;
import com.squintero.medicinapp.data.roomDao.MedicineDao;
import com.squintero.medicinapp.data.roomDao.PersonDao;

//DOC: https://developer.android.com/training/data-storage/room/defining-data.html
@Database(entities = {PersonData.class, MedicineData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract PersonDao personDao();
    public abstract MedicineDao medicineDao();
}
