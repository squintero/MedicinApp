package com.squintero.medicinapp.data;

import android.arch.persistence.room.Room;
import android.content.Context;

public class DatabaseModel {
    private static final DatabaseModel ourInstance = new DatabaseModel();

    public static DatabaseModel getInstance() {
        return ourInstance;
    }

    private DatabaseModel() {
    }

    //--------------
    private AppDatabase db = null;

    public AppDatabase getDbInstance(Context context){

        if (db == null)
            db = Room.databaseBuilder(context, AppDatabase.class, "medicinapp-db").build();

        return db;
    }
}
