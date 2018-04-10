package com.squintero.medicinapp.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.squintero.medicinapp.utilities.DialogMessageManager;


public class BaseFragment extends Fragment implements BaseView {

    private AppCompatActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AppCompatActivity) {
            mActivity = (AppCompatActivity) context;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /** BaseView **/
    @Override
    public View.OnClickListener onClickAction() {
        return null;
    }

    @Override
    public void showMessage(String message) {

        if (message == null)
            return;

        DialogMessageManager.showSingleAlert(mActivity, message);
    }

    @Override
    public void showSnackBar(String message) {

        if (message == null)
            return;

        DialogMessageManager.showSnackBar(
                mActivity.findViewById(android.R.id.content),
                message,
                null,
                null);
    }

    @Override
    public void showToast(String message) {

        if (message == null)
            return;

        DialogMessageManager.showToast(mActivity, message, Toast.LENGTH_SHORT);
    }
}
