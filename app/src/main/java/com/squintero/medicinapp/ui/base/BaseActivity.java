package com.squintero.medicinapp.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.squintero.medicinapp.utilities.DialogMessageManager;
import com.squintero.medicinapp.utilities.Logs;


public class BaseActivity extends AppCompatActivity implements BaseView{

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logs.SystemLog(TAG + " - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logs.SystemLog(TAG + " - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logs.SystemLog(TAG + " - onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logs.SystemLog(TAG + " - onDestroy");
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

        DialogMessageManager.showSingleAlert(this, message);
    }

    @Override
    public void showSnackBar(String message) {

        if (message == null)
            return;

        DialogMessageManager.showSnackBar(
                findViewById(android.R.id.content),
                message,
                null,
                null);
    }

    @Override
    public void showToast(String message) {

        if (message == null)
            return;

        DialogMessageManager.showToast(this, message, Toast.LENGTH_SHORT);
    }
}
