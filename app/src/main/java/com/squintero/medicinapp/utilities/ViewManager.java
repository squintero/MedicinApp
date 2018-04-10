package com.squintero.medicinapp.utilities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.squintero.medicinapp.App;


public final class ViewManager {

    public static void fragmentAdd(View container, FragmentManager fragmentManager,
                                   Fragment fragment, String tag,
                                   int enterAnim, int exitAnim, int popEnter, int popExit,
                                   boolean addBackStack)
    {
        if (fragment == null)
            return;

        try {

            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (enterAnim != -1)  //Animation
                ft.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);

            ft.add(container.getId(), fragment, tag);

            if (addBackStack)
                ft.addToBackStack(tag);

            ft.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void fragmentReplace(View container, FragmentManager fragmentManager,
                                       Fragment fragment, String tag,
                                       int enterAnim, int exitAnim, int popEnter, int popExit,
                                       boolean addBackStack)
    {
        if (fragment == null)
            return;

        try {

            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (enterAnim != -1)  //Animation
                ft.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);

            ft.replace(container.getId(), fragment, tag);

            if (addBackStack)
                ft.addToBackStack(tag);

            ft.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void fragmentRemove(FragmentManager fragmentManager, Fragment fragment,
                                      int enterAnim, int exitAnim, int popEnter, int popExit)
    {
        if (fragment != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();

            if (enterAnim != -1)  //Animations
                ft.setCustomAnimations(enterAnim, exitAnim, popEnter, popExit);

            ft.remove(fragment);
            ft.commit();
        }
    }

    public static void launchActivity(Object currentActivity, Class invokedActivity, Intent extras) {

        Intent i = new Intent(App.getInstance(), invokedActivity);

        if (extras != null)
            i.putExtras(extras);

        if (!currentActivity.getClass().equals(Activity.class)){
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            i.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        }

        App.getInstance().startActivity(i);
    }
}
