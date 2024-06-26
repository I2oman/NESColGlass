package com.example.nescolglass;

import static com.example.nescolglass.Globals.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LocalStorage {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor prefsEditor;
    private Context context;

    // Constructor initializes shared preferences
    public LocalStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        prefsEditor = sharedPreferences.edit();
    }

    // Method to store preferences based on key-value pairs
    public static <T> void putPrefs(String KEY, T value) {
        // Determine the type of value and store accordingly
        if (value instanceof String) {
            prefsEditor.putString(KEY, (String) value);
        } else if (value instanceof Boolean) {
            prefsEditor.putBoolean(KEY, (Boolean) value);
        } else if (value instanceof Float) {
            prefsEditor.putFloat(KEY, (Float) value);
        } else if (value instanceof Integer) {
            prefsEditor.putInt(KEY, (Integer) value);
        }
        prefsEditor.apply(); // Apply changes
    }

    // Method to retrieve preferences based on key and return type
    public static <T> T getPrefs(String KEY, Class<T> returnType) {
        if (returnType == String.class) {
            return (T) sharedPreferences.getString(KEY, "");
        } else if (returnType == Boolean.class) {
            return (T) (Boolean) sharedPreferences.getBoolean(KEY, false);
        } else if (returnType == Float.class) {
            return (T) (Float) sharedPreferences.getFloat(KEY, Float.MIN_VALUE);
        } else if (returnType == Integer.class) {
            return (T) (Integer) sharedPreferences.getInt(KEY, Integer.MIN_VALUE);
        } else {
            throw new IllegalArgumentException("Unsupported return type");
        }
    }

    // Method to log all preferences
    public static void showPrefs() {
        Log.i("System.out.println()", sharedPreferences.getAll().toString());
    }
}
