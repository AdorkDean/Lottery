package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */


import android.content.Context;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtils {
    public static void putBoolean(String key, Context context, boolean f) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.putBoolean(key, f);
        editor.commit();
    }

    public static boolean getBoolean(String key, Context context) {
        return context.getSharedPreferences("zgzzzs_sp", 0).getBoolean(key, false);
    }

    public static void putString(String key, Context context, String f) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.putString(key, f);
        editor.commit();
    }

    public static String getString(String key, Context context) {
        return context.getSharedPreferences("zgzzzs_sp", 0).getString(key, null);
    }

    public static void putName(String key, Context context, String name) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.putString(key, name);
        editor.commit();
    }

    public static String getName(String key, Context context) {
        return context.getSharedPreferences("zgzzzs_sp", 0).getString(key, null);
    }

    public static void putPwd(String key, Context context, String name) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.putString(key, name);
        editor.commit();
    }

    public static String getPwd(String key, Context context) {
        return context.getSharedPreferences("zgzzzs_sp", 0).getString(key, null);
    }

    public static void putUserJsonStr(String key, Context context, String str) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.putString(key, str);
        editor.commit();
    }

    public static String getUserJsonStr(String key, Context context) {
        return context.getSharedPreferences("zgzzzs_sp", 0).getString(key, "nologinmessage");
    }

    public static void clearAllDate(Context context) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.clear();
        editor.commit();
    }

    public static void clearDate(Context context, String key) {
        Editor editor = context.getSharedPreferences("zgzzzs_sp", 0).edit();
        editor.remove(key);
        editor.commit();
    }
}