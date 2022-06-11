package com.example.project_roommate.model;

import android.content.Context;
import android.content.SharedPreferences;

public class ModelSharedPreferences {
    private static final String USER_INFO = "userInfo";

    public static void setUserLocation(Context context, String input) {
        SharedPreferences prefs = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("location", input);
        editor.apply();
    }

    public static String getUserLocation(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return prefs.getString("location", "").toString();
    }
}
