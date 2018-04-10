package com.squintero.medicinapp.utilities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.squintero.medicinapp.App;


public final class BroadcastManager {

    public static void broadcastRegister(BroadcastReceiver receptor, String[] actions) {

        for (int i = 0; i < actions.length; i++)
            LocalBroadcastManager.getInstance(App.getInstance()).registerReceiver(receptor, new IntentFilter(actions[i]));
    }

    public static void broadcastSend(String action, Bundle extras) {

        Intent intent = new Intent();
        intent.setAction(action);

        if (extras != null)
            intent.putExtras(extras);

        LocalBroadcastManager.getInstance(App.getInstance()).sendBroadcast(intent);
    }

    public static void broadcastUnregister(BroadcastReceiver receptor) {

        LocalBroadcastManager.getInstance(App.getInstance()).unregisterReceiver(receptor);
    }
}
