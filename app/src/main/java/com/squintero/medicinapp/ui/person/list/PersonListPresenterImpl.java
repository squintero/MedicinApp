package com.squintero.medicinapp.ui.person.list;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.squintero.medicinapp.data.model.PersonData;
import com.squintero.medicinapp.ui.base.BasePresenterImpl;
import com.squintero.medicinapp.ui.person.PersonActivity;
import com.squintero.medicinapp.ui.person.PersonPresenter;
import com.squintero.medicinapp.ui.person.PersonView;


public class PersonListPresenterImpl extends BasePresenterImpl<PersonView.PersonList> implements
        PersonPresenter.PersonList {

    private Context context;
    private PersonActivity parentView;
    private PersonView.PersonList view;

    public PersonListPresenterImpl(Context context, PersonView.PersonList view) {
        this.parentView = (PersonActivity) context;
        this.context    = context;
        this.view       = view;
    }

    @Override
    public void attachView(PersonView.PersonList baseView) {
        super.attachView(baseView);

    }

    @Override
    public void detachView() {
        super.detachView();

    }

    /** PersonPresenter.PersonList **/
    @Override
    public void getList() {
        //TODO
    }

    @Override
    public void onClickPerson(PersonData personData) {
        //TODO
    }

    @Override
    public void removePerson(PersonData personData) {
        //TODO
    }
}
