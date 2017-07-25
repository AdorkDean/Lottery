package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;

import com.jz.lottery.BuildConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class PreferencesObjectUtil {
    private Context context;

    public PreferencesObjectUtil(Context context) {
        this.context = context;
    }

    public static Object readObject(String key, Context context) {
        Object obj = null;
        try {
            try {
                obj = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(context.getSharedPreferences("base64", 0).getString(key, BuildConfig.VERSION_NAME).getBytes(), 0))).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
            } catch (EOFException e2) {
                e2.printStackTrace();
                Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
            }
        } catch (StreamCorruptedException e3) {
            e3.printStackTrace();
            Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
        } catch (IOException e4) {
            e4.printStackTrace();
            Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
        }
        return obj;
    }

    public static void saveObject(Object obj, String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences("base64", 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(baos).writeObject(obj);
            String obj_base64 = new String(Base64.encode(baos.toByteArray(), 0));
            Editor editor = preferences.edit();
            editor.putString(key, obj_base64);
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("ok", "\u5b58\u50a8\u5931\u8d25");
        }
    }

    public Object readObject(String key) {
        Object obj = null;
        try {
            try {
                obj = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(this.context.getSharedPreferences("base64", 0).getString(key, BuildConfig.VERSION_NAME).getBytes(), 0))).readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
            }
        } catch (StreamCorruptedException e2) {
            e2.printStackTrace();
            Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
        } catch (IOException e3) {
            e3.printStackTrace();
            Log.i("ok", "\u8bfb\u53d6\u5931\u8d25");
        }
        return obj;
    }

    public void saveObject(Object obj, String key) {
        SharedPreferences preferences = this.context.getSharedPreferences("base64", 0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(baos).writeObject(obj);
            String obj_base64 = new String(Base64.encode(baos.toByteArray(), 0));
            Editor editor = preferences.edit();
            editor.putString(key, obj_base64);
            editor.commit();
        } catch (IOException e) {
            Log.i("ok", "\u5b58\u50a8\u5931\u8d25");
        }
    }

    public static void clearAllDate(Context context) {
        Editor editor = context.getSharedPreferences("base64", 0).edit();
        editor.clear();
        editor.commit();
    }

    public static void clearDate(Context context, String key) {
        Editor editor = context.getSharedPreferences("base64", 0).edit();
        editor.remove(key);
        editor.commit();
    }
}