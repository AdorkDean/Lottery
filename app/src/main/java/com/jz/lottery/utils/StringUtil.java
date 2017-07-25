package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.DisplayMetrics;
import android.widget.Toast;

import com.jz.lottery.BuildConfig;

import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;

import cn.bmob.v3.BmobConstants;


public class StringUtil {
    public static SimpleDateFormat mDateFormat;
    public static SimpleDateFormat nDateFormat;
    public static String[] numberCapital;

    /* renamed from: com.wwp.www.vrcpchaxun.utils.StringUtil.1 */
    static class AnonymousClass1 extends Thread {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$dir;

        AnonymousClass1(Context context, String str) {
            this.val$context = context;
            this.val$dir = str;
        }

        public void run() {
            String savePath = this.val$context.getCacheDir().getAbsolutePath() + "/" + this.val$dir + "/";
            File upedu = new File(savePath);
            if (upedu.exists()) {
                StringUtil.deleteFolderFile(savePath + upedu, true);
            }
            upedu.mkdirs();
        }
    }

    static {
        mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        nDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        numberCapital = new String[]{"\u96f6", "\u4e00", "\u4e8c", "\u4e09", "\u56db", "\u4e94", "\u516d", "\u4e03", "\u516b", "\u4e5d"};
    }

    public static String getStringDateShort() {
        return mDateFormat.format(new Date());
    }

    public static String getStringDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE).format(new Date());
    }

    public static String getTimeShort(long milliseconds) {
        return new SimpleDateFormat("HH:mm:ss").format(new Date(milliseconds));
    }

    public static long getLongDate(String stringData, String styleData) {
        long longDate = 0;
        try {
            longDate = new SimpleDateFormat(styleData).parse(stringData).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return longDate;
    }

    public static boolean isEmpty(CharSequence input) {
        if (input == null || BuildConfig.VERSION_NAME.equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotEmpty(String s) {
        return s != null && s.trim().length() > 0;
    }

    public static String getRandom() {
        return String.valueOf(new Random().nextInt(20) + 40);
    }

    public static String readAssets(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();
        if (fileName == null || fileName.equals(BuildConfig.VERSION_NAME)) {
            return null;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), HTTP.UTF_8));
            sb.append(br.readLine());
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String readFromFile(Context context, String fileName) {
        Exception e;
        StringBuilder sb = new StringBuilder();
        if (fileName == null || fileName.equals(BuildConfig.VERSION_NAME)) {
            return null;
        }
        try {
            InputStream is = new FileInputStream(context.getFileStreamPath(fileName));
            InputStream inputStream;
            try {
                InputStreamReader isr = new InputStreamReader(is, HTTP.UTF_8);
                char[] charStr = new char[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
                while (true) {
                    int c = isr.read(charStr);
                    if (c != -1) {
                        sb.append(charStr, 0, c);
                    } else {
                        is.close();
                        inputStream = is;
                        return sb.toString();
                    }
                }
            } catch (Exception e2) {
                e = e2;
                inputStream = is;
                e.printStackTrace();
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            e.printStackTrace();
            return null;
        }
    }

    public static int getChineseLength(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uffe5]";
        for (int i = 0; i < value.length(); i++) {
            if (value.substring(i, i + 1).matches(chinese)) {
                valueLength++;
            }
        }
        return valueLength;
    }

    public static int getLengthForUpdate(String value) {
        int cLength = 0;
        int eLength = 0;
        String chinese = "[\u0391-\uffe5]";
        for (int i = 0; i < value.length(); i++) {
            if (value.substring(i, i + 1).matches(chinese)) {
                cLength += 2;
            } else {
                eLength++;
            }
        }
        return cLength + eLength;
    }

    public static int getStrLength(String value) {
        int cLength = 0;
        int eLength = 0;
        String chinese = "[\u0391-\uffe5]";
        for (int i = 0; i < value.length(); i++) {
            if (value.substring(i, i + 1).matches(chinese)) {
                cLength++;
            } else {
                eLength++;
            }
        }
        return ((eLength / 2) + cLength) + (eLength % 2);
    }

    public static String ToNumber(double num, int p) {
        if (Double.isNaN(num)) {
            return "--";
        }
        num = new BigDecimal(num).setScale(p, 4).doubleValue();
        return String.format("%1$." + p + "f", new Object[]{Double.valueOf(num)});
    }

    public static String ToDate(Date date) {
        return mDateFormat.format(date);
    }

    public static Date ParseDate(String date) {
        try {
            return mDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getBeforeDot(String price) {
        if (!isNotEmpty(price) || -1 == price.indexOf(".")) {
            return BuildConfig.VERSION_NAME;
        }
        return price.substring(0, price.indexOf("."));
    }

    private static String addZero(int str) {
        String tempStr = String.valueOf(str);
        if (1 == tempStr.length()) {
            return "0" + tempStr;
        }
        return tempStr;
    }

    public static String formatLongToTimeStr(Long l) {
        String strtime = BuildConfig.VERSION_NAME;
        if (l.longValue() <= 0) {
            return "00:00:00";
        }
        int hour = 0;
        int minute = 0;
        int second = l.intValue() / BmobConstants.TIME_DELAY_RETRY;
        if (second > 60) {
            minute = second / 60;
            second %= 60;
        }
        if (minute > 60) {
            hour = minute / 60;
            minute %= 60;
        }
        return addZero(hour) + ":" + addZero(minute) + ":" + addZero(second);
    }

    public static String toUnicode(String s) {
        String s1 = BuildConfig.VERSION_NAME;
        for (int i = 0; i < s.length(); i++) {
            s1 = s1 + "\\u" + Integer.toHexString(s.charAt(i) & SupportMenu.USER_MASK);
        }
        return s1;
    }

    public static String replaceUrlWithPlus(String url) {
        if (url != null) {
            return url.replaceAll("http://(.)*?/", BuildConfig.VERSION_NAME).replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
        }
        return null;
    }

    public static int getLength(String content) {
        String anotherString = BuildConfig.VERSION_NAME;
        try {
            anotherString = new String(content.getBytes("GBK"), "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return anotherString.length();
    }

    public static String readAssetsCity(Context context, String fileName) {
        StringBuilder sb = new StringBuilder();
        if (fileName == null || fileName.equals(BuildConfig.VERSION_NAME)) {
            return null;
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open(fileName), HTTP.UTF_8));
            sb.append(br.readLine());
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(line);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String MobileMask(String mobile) {
        if (!isNotEmpty(mobile) || mobile.length() <= 3) {
            return "****";
        }
        if (mobile.length() > 7) {
            return mobile.substring(0, 3) + "****" + mobile.substring(7);
        }
        return mobile.substring(0, 3) + "****";
    }

    public static boolean isInstalled(Context context, String packageName) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo != null) {
            return true;
        }
        return false;
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size += getFolderSize(fileList[i]);
                } else {
                    size += fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = BuildConfig.VERSION_NAME;
        if (fileS == 0) {
            return BuildConfig.VERSION_NAME;
        }
        if (fileS < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID && fileS > 0) {
            return df.format((double) fileS) + "B";
        }
        if (fileS < 1048576) {
            return df.format(((double) fileS) / 1024.0d) + "K";
        }
        if (fileS < 1073741824) {
            return df.format(((double) fileS) / 1048576.0d) + "M";
        }
        return df.format(((double) fileS) / 1.073741824E9d) + "G";
    }

    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File[] files = file.listFiles();
                    for (File absolutePath : files) {
                        deleteFolderFile(absolutePath.getAbsolutePath(), true);
                    }
                }
                if (!deleteThisPath) {
                    return;
                }
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length != 0) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getFilePath(String vid, Context context, String drm) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File dir = new File(Environment.getExternalStorageDirectory() + "/xuepaiVideo");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String path = dir + "/" + vid;
            if (drm == null || !drm.equals("1")) {
                return path + ".mp4";
            }
            return path + ".pcm";
        }
        Toast.makeText(context, "sd\u5361\u4e0d\u5b58\u5728\uff01", Toast.LENGTH_SHORT).show();
        return null;
    }

    public static String getPhoneType() {
        return Build.MODEL;
    }

    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    public static String getScreenSize(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return String.valueOf(metric.widthPixels) + "*" + String.valueOf(metric.heightPixels);
    }

    public static String getScreenWidthSize(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return String.valueOf(metric.widthPixels);
    }

    public static String getScreenHeightSize(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return String.valueOf(metric.heightPixels);
    }

    public static void buildDir(Context context, String dir) {
        new AnonymousClass1(context, dir).start();
    }

    public static boolean getCpuArchitecture() {
        boolean isArmCPU = false;
        InputStream is = null;
        InputStreamReader ir = null;
        BufferedReader br = null;
        try {
             is = new FileInputStream("/proc/cpuinfo");
             ir = new InputStreamReader(is);
             br = new BufferedReader(ir);
            char[] data = new char[AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT];
            br.read(data);
            String valueOf = String.valueOf(data);
            if (isEmpty(valueOf) || !valueOf.contains("ARM")) {
                isArmCPU = false;
            } else {
                isArmCPU = true;
            }
            br.close();
            ir.close();
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            try {
                br.close();
                is.close();
                ir.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isArmCPU;
    }

    public static String numberLower2Capital(int num) {
        if (num <= 0) {
            return BuildConfig.VERSION_NAME;
        }
        String numLower = String.valueOf(num);
        ArrayList<String> arrayList = new ArrayList();
        int n = 1;
        for (int i = numLower.length() - 1; i > -1; i--) {
            arrayList.add(0, numberCapital[Integer.valueOf(String.valueOf(numLower.charAt(i))).intValue()]);
            n++;
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
        }
        return stringBuffer.toString();
    }
}