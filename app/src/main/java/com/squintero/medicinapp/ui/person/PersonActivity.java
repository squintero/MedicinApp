package com.squintero.medicinapp.ui.person;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.RelativeLayout;

import com.squintero.medicinapp.App;
import com.squintero.medicinapp.R;
import com.squintero.medicinapp.databinding.ActivityPersonBinding;
import com.squintero.medicinapp.ui.base.BaseActivity;
import com.squintero.medicinapp.utilities.Utils;


public class PersonActivity extends BaseActivity implements PersonView.Activity {

    private ActivityPersonBinding mBinding;
    private PersonPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init(R.layout.activity_person);
    }

    @Override
    protected void onStart() {
        super.onStart();

        App.getInstance().setCurrentActivity(this);
        presenter.attachView(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.detachView();
    }

    @Override
    public void onBackPressed() {

        Utils.hideKeyboard(this);

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();

            return;
        }

        super.onBackPressed();
    }

    private void init(int layout) {

        presenter = new PersonPresenterImpl(this);
        mBinding  = DataBindingUtil.setContentView(this, layout);
        mBinding.setView(this);
//        mBinding.incToolbarTop.setListener(onClickAction());

//        presenter.checkNextViewToLoad();
    }

    @Override
    public View.OnClickListener onClickAction() {
        return new PersonListener(this, this, this).Activity;
    }

    /** PersonView.Activity **/

}
