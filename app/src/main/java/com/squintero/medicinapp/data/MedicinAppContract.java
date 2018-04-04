package com.squintero.medicinapp.data;

import android.provider.BaseColumns;

/**
 * Defines table and column names for the weather database. This class is not necessary, but keeps
 * the code organized.
 */
public class MedicinAppContract {

    /* Inner class that defines the table contents of the person table */
    public static final class PersonEntry implements BaseColumns {

        /* Used internally as the name of our person table. */
        public static final String TABLE_NAME = "person";

        public static final String COLUMN_PERSON_NAME = "person_name";

        public static final String COLUMN_PERSON_SURNAME = "person_surname";

        public static final String COLUMN_PERSON_AGE = "person_age";

        public static final String COLUMN_PERSON_EXTRA_INFO = "person_extra_info";

    }

    /* Inner class that defines the table contents of the medicine table */
    public static final class MedicineEntry implements BaseColumns {

        /* Used internally as the name of our medicine table. */
        public static final String TABLE_NAME = "medicine";

        public static final String COLUMN_MEDICINE_NAME = "medicine_name";

        public static final String COLUMN_MEDICINE_EXTRA_INFO = "medicine_extra_info";

    }
}