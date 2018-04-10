package com.squintero.medicinapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;


import com.squintero.medicinapp.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class DialogMessageManager {

    public static void showSingleAlert(final Context context, String message){

        if (context == null)
            return;

        new android.support.v7.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void showSingleAlert(final Context context, String title, String message){

        if (context == null)
            return;

        new android.support.v7.app.AlertDialog.Builder(context, R.style.MyAlertDialogStyle)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public static void showSingleAlertWithReflection(final Activity activity, final Object currentClass,
                                                     String text, final String processName)
    {
        if (activity == null)
            return;

        new android.support.v7.app.AlertDialog.Builder(activity, R.style.MyAlertDialogStyle)
                .setMessage(text)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (processName == null)
                            return;

                        try {
                            Method method = currentClass.getClass().getMethod(processName);
                            method.invoke(currentClass);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .create()
                .show();
    }

    public static void showSingleAlertWithReflectionTwoButtons(final Activity activity,
                                                               final Object currentClass,
                                                               String buttonOK, String buttonCancel,
                                                               String text,
                                                               final boolean finishActivity,
                                                               final String processName)
    {
        if (activity == null)
            return;

        new android.support.v7.app.AlertDialog.Builder(activity, R.style.MyAlertDialogStyle)
                .setMessage(text)
                .setNegativeButton(buttonCancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (finishActivity)
                            activity.finish();
                    }
                })
                .setPositiveButton(buttonOK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (processName == null) {
                            if (finishActivity)
                                activity.finish();

                            return;
                        }

                        try {
                            Method method = currentClass.getClass().getMethod(processName);
                            method.invoke(currentClass);

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .create()
                .show();
    }

//    public static void showSnackBar(View view, String message, String actionText,
//                                    View.OnClickListener onClickListener)
//    {
//        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionText, onClickListener).show();
//    }

    public static void showToast(Context context, String message, int duration) {

        Toast.makeText(context, message, duration).show();
    }
}
