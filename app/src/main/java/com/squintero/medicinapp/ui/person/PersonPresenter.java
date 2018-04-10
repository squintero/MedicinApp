package com.squintero.medicinapp.ui.person;


import android.app.Activity;

import com.squintero.medicinapp.data.model.PersonData;

public interface PersonPresenter {

    interface Activity {
    }

    interface PersonAddEdit {
        void onClickConfirm();

        void prepareView();
    }

    interface PersonList {
        void getList();

        void onClickPerson(PersonData personData);

        void removePerson(PersonData personData);
    }
}
