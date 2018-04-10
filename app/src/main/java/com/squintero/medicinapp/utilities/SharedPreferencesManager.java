package com.squintero.medicinapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.squintero.medicinapp.App;
import com.squintero.medicinapp.Constants;


public final class SharedPreferencesManager {

    private static SharedPreferencesManager instance;
    private final SharedPreferences sharedPreferences;

    public static SharedPreferencesManager getInstance() {
        if (instance == null) instance = new SharedPreferencesManager();
        return instance;
    }

    public SharedPreferencesManager() {
        sharedPreferences = App.getInstance().getSharedPreferences(Constants.SHARED_NAME_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();;
    }

    public boolean getBooleanValue(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public boolean getBooleanValue(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public float getFloatValue(String key) {
        return sharedPreferences.getFloat(key, 0.0f);
    }

    public int getIntegerValue(String key) {
        return sharedPreferences.getInt(key, -1);
    }

    public String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public String getStringValue(String key, boolean nullable) {
        if (nullable) {
            return sharedPreferences.getString(key, null);
        } else {
            return sharedPreferences.getString(key, "");
        }
    }

    public void removeValue(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void save(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public void save(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    public void save(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public void save(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
}
