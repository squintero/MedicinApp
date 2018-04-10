package com.squintero.medicinapp.ui.person;

import android.app.Activity;
import android.view.View;

import com.squintero.medicinapp.R;
import com.squintero.medicinapp.ui.base.BaseActivity;


public class PersonListener {

    private BaseActivity activity;
    private PersonView.Activity activityView;
    private Object parentView;

    public PersonListener(Activity activity, PersonView.Activity activityView, Object parentView) {
        this.activity     = (BaseActivity) activity;
        this.activityView = activityView;
        this.parentView   = parentView;
    }

    public View.OnClickListener Activity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                //TODO button back ??
//                case R.id.ibBack:
//                    activity.onBackPressed();
//                    break;
            }
        }
    };


    public View.OnClickListener PersonAddEdit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            PersonView.PersonAddEdit personAddEdit = (PersonView.PersonAddEdit) parentView;

            switch (view.getId()) {
                //TODO confirm action ??
//                case R.id.btnConfirm:
//                    personAddEdit.confirmAction();
//                    break;
            }
        }
    };

    public View.OnClickListener PersonList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            PersonView.PersonList personList = (PersonView.PersonList) parentView;

            switch (view.getId()) {
                //TODO button add person ??
//                case R.id.btnAddPerson:
//                    personList.addAction();
//                    break;
            }
        }
    };

}
