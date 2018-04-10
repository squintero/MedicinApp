package com.squintero.medicinapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;


public class App extends Application {

    private static App singleton;

    public static App getInstance(){
        return singleton;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
//    }

    //Create the app
    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;
    }

    //Home animations
    public final static int NO_ANIMATION     = -1;
    public final static int FADE_IN          = R.anim.fade_in;
    public final static int FADE_OUT         = R.anim.fade_out;
    public final static int SLIDE_IN_TOP     = R.anim.slide_in_top;
    public final static int SLIDE_IN_BOTTOM  = R.anim.slide_in_bottom;
    public final static int SLIDE_IN_LEFT    = R.anim.slide_in_left;
    public final static int SLIDE_IN_RIGHT   = R.anim.slide_in_right;
    public final static int SLIDE_OUT_TOP    = R.anim.slide_out_top;
    public final static int SLIDE_OUT_BOTTOM = R.anim.slide_out_bottom;
    public final static int SLIDE_OUT_LEFT   = R.anim.slide_out_left;
    public final static int SLIDE_OUT_RIGHT  = R.anim.slide_out_right;


    /**********************/
    /** CURRENT ACTIVITY **/
    /**********************/
    public AppCompatActivity currentActivity;

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

}
