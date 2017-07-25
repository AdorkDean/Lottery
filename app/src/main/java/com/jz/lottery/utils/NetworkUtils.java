package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import  android.net.http.Headers;
import android.telephony.TelephonyManager;

import java.util.List;

public class NetworkUtils {
    public static boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();
        if (networkInfo == null || networkInfo.length <= 0) {
            return false;
        }
        for (NetworkInfo state : networkInfo) {
            if (state.getState() == State.CONNECTED) {
                return true;
            }
        }
        return false;
    }

    public static boolean isGpsEnabled(Context context) {
        List<String> accessibleProviders = ((LocationManager) context.getSystemService(Headers.LOCATION)).getProviders(true);
        if (accessibleProviders == null || accessibleProviders.size() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context.getSystemService("connectivity");
        return (mgrConn.getActiveNetworkInfo() != null && mgrConn.getActiveNetworkInfo().getState() == State.CONNECTED) || ((TelephonyManager) context.getSystemService("phone")).getNetworkType() == 3;
    }

    public static boolean isWifi(Context context) {
        NetworkInfo activeNetInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetInfo == null || activeNetInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    public static boolean is3G(Context context) {
        NetworkInfo activeNetInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetInfo == null || activeNetInfo.getType() != 0) {
            return false;
        }
        return true;
    }
}