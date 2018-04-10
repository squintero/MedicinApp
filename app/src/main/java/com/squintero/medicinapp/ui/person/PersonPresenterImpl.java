package com.squintero.medicinapp.ui.person;


import android.content.Context;


import com.squintero.medicinapp.ui.base.BaseActivity;
import com.squintero.medicinapp.ui.base.BasePresenterImpl;



public class PersonPresenterImpl extends BasePresenterImpl<PersonView.Activity> implements
        PersonPresenter.Activity {
    private BaseActivity activity;
    private Context context;
    private PersonView.Activity view;

    public PersonPresenterImpl(PersonView.Activity view) {
        this.context  = (Context) view;
        this.activity = (BaseActivity) view;
        this.view     = view;
    }

    @Override
    public void attachView(PersonView.Activity baseView) {
        super.attachView(baseView);

    }

    @Override
    public void detachView() {
        super.detachView();

    }

    /** PersonPresenter.Activity **/

}
