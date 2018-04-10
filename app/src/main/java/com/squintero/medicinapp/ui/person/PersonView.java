package com.squintero.medicinapp.ui.person;


import com.squintero.medicinapp.data.model.PersonData;
import com.squintero.medicinapp.ui.base.BaseView;

import java.util.List;

public interface PersonView {

    interface Activity extends BaseView {
//        void loadingShow(boolean show);
    }

    interface PersonAddEdit extends BaseView {

        void confirmAction();
    }

    interface PersonList extends BaseView {

        void addAction();

        void loadList();

        void populateList(List<PersonData> personDataList);

        void updateList(List<PersonData> personDataList);
    }

}
