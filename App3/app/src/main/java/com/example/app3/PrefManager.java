package com.example.app3;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class PrefManager {
    // Shared Preferences
    private SharedPreferences sharedPreferences;
    // Editor for Shared preferences
    private SharedPreferences.Editor editor;
    // Context
    private Context context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // SharedPreferences file name
    private static final String PREF_NAME = "userSessionPref";

    public PrefManager(Context context){
        this.context = context;


        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public Object get(String key, Class c){
        if(c == Boolean.class){
            return sharedPreferences.getBoolean(key, false);
        }

        if(c == String.class){
            return sharedPreferences.getString(key, null);
        }

        return null;
    }

    public Set<String> getStringSet(String key){
        return sharedPreferences.getStringSet(key, null);
    }

    public void put(String key, Object value){
        if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }

        if(value instanceof String){
            editor.putString(key, (String) value);
        }

        if(value instanceof Integer){
            editor.putInt(key, (int) value);
        }

        editor.commit();
    }

    public void putStringSet(String key, String value){
        Set<String> set = new HashSet<>();

        if(sharedPreferences.getStringSet(key, null) != null) {
            set = sharedPreferences.getStringSet(key, null);
            set.add(value);
        }else{
            set.add(value);
        }

        editor.putStringSet(key, set);
        editor.commit();
    }

    //*Funciones no dinámicas
//    public void putString(String set, String value){
//        editor.putString(set, value);
//        editor.commit();
//    }
//
//    public void putBoolean(String set, Boolean value){
//        editor.putBoolean(set, value);
//        editor.commit();
//    }

    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    public Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }
}

