package com.squintero.medicinapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Manages a local database for data.
 */
public class MedicinAppDbHelper extends SQLiteOpenHelper {

    /*
     * This is the name of our database. Database names should be descriptive and end with the
     * .db extension.
     */
    public static final String DATABASE_NAME = "medicinapp.db";

    //  TODO (2) Increment the database version after changing the create table statement
    /*
     * If you change the database schema, you must increment the database version or the onUpgrade
     * method will not be called.
     *
     * The reason DATABASE_VERSION starts at 3 is because Sunshine has been used in conjunction
     * with the Android course for a while now. Believe it or not, older versions of Sunshine
     * still exist out in the wild. If we started this DATABASE_VERSION off at 1, upgrading older
     * versions of Sunshine could cause everything to break. Although that is certainly a rare
     * use-case, we wanted to watch out for it and warn you what could happen if you mistakenly
     * version your databases.
     */
    private static final int DATABASE_VERSION = 1;

    public MedicinAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the creation of
     * tables and the initial population of the tables should happen.
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
         * This String will contain a simple SQL statement that will create a table that will store people data
         */
        final String SQL_CREATE_PERSON_TABLE =

                "CREATE TABLE " + MedicinAppContract.PersonEntry.TABLE_NAME + " (" +

                        /*
                         * MedicinAppContract.PersonEntry did not explicitly declare a column called "_ID". However,
                         * MedicinAppContract.PersonEntry implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MedicinAppContract.PersonEntry._ID                      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        MedicinAppContract.PersonEntry.COLUMN_PERSON_NAME       + " TEXT NOT NULL, " +

                        MedicinAppContract.PersonEntry.COLUMN_PERSON_SURNAME    + " TEXT NOT NULL, " +

                        MedicinAppContract.PersonEntry.COLUMN_PERSON_AGE        + " INTEGER NOT NULL, " +

                        MedicinAppContract.PersonEntry.COLUMN_PERSON_EXTRA_INFO + " TEXT NOT NULL" + ");";

        final String SQL_CREATE_MEDICINE_TABLE =

                "CREATE TABLE " + MedicinAppContract.MedicineEntry.TABLE_NAME + " (" +

                        /*
                         * MedicinAppContract.PersonEntry did not explicitly declare a column called "_ID". However,
                         * MedicinAppContract.PersonEntry implements the interface, "BaseColumns", which does have a field
                         * named "_ID". We use that here to designate our table's primary key.
                         */
                        MedicinAppContract.MedicineEntry._ID                      + " INTEGER PRIMARY KEY AUTOINCREMENT, " +

                        MedicinAppContract.MedicineEntry.COLUMN_MEDICINE_NAME       + " TEXT NOT NULL, " +

                        MedicinAppContract.MedicineEntry.COLUMN_MEDICINE_EXTRA_INFO + " TEXT NOT NULL" + ");";


        /*
         * After we've spelled out our SQLite table creation statement above, we actually execute
         * that SQL with the execSQL method of our SQLite database object.
         */
        sqLiteDatabase.execSQL(SQL_CREATE_PERSON_TABLE);

        sqLiteDatabase.execSQL(SQL_CREATE_MEDICINE_TABLE);

        //TODO table person - medicine
    }

    /**
     * @param sqLiteDatabase Database that is being upgraded
     * @param oldVersion     The old database version
     * @param newVersion     The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        //TODO prepare a migration

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MedicinAppContract.PersonEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MedicinAppContract.MedicineEntry.TABLE_NAME);

        //TODO table person - medicine

        onCreate(sqLiteDatabase);
    }
}