package com.team.bulkan.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.team.bulkan.R;

/**
 * Created by Kira on 9/21/2017.
 */

public class PrefManager {

    public static final String IS_TRACKED = "IS_TRACKED";
    public static final String FB_DEVICE_TOKEN = "FB_DEVICE_TOKEN";
    public static final String IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";

    private static PrefManager instance = null;
    private SharedPreferences preferences;

    public static PrefManager getInstance(Context context) {
        if (instance == null)
            instance = new PrefManager(context);
        return instance;
    }

    private PrefManager(Context context) {
        String PACKAGE_NAME = context.getPackageName();
        String name = PACKAGE_NAME + "." + context.getResources().getString(R.string.shared_prefs_key);
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    //STRING
    public void save(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    //INT
    public void save(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    //BOOLEAN
    public void save(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public void setIsFirstTimeLaunch(String key, boolean value){
        preferences.edit().putBoolean(key,value).apply();
    }

    public boolean getIsFirstTimeLaunch(){
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

}
