package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class StringToTimeUtils {
    public static String setTime(String str_time) {
        long t = Long.parseLong(str_time);
        return ((int) (t / 3600000)) + ":" + ((int) (((t / 1000) / 60) % 60)) + ":" + ((int) ((t / 1000) % 60));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String setTime2(long r24) {
        /*
        r13 = 8;
        r22 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r20 = r24 / r22;
        r22 = 28800; // 0x7080 float:4.0357E-41 double:1.4229E-319;
        r0 = r22;
        r0 = (long) r0;
        r22 = r0;
        r20 = r20 + r22;
        r22 = 60;
        r22 = r20 % r22;
        r0 = r22;
        r12 = (int) r0;
        r22 = 60;
        r18 = r20 / r22;
        r22 = 60;
        r22 = r18 % r22;
        r0 = r22;
        r9 = (int) r0;
        r22 = 60;
        r16 = r18 / r22;
        r22 = 24;
        r22 = r16 % r22;
        r0 = r22;
        r6 = (int) r0;
        r22 = 24;
        r22 = r16 / r22;
        r0 = r22;
        r14 = (int) r0;
        r2 = 1970; // 0x7b2 float:2.76E-42 double:9.733E-321;
        r0 = r14 / 366;
        r22 = r0;
        r15 = r2 + r22;
        r10 = 1;
        r3 = 1;
    L_0x003d:
        r22 = r15 - r2;
        r0 = r22;
        r4 = r0 * 365;
        r22 = r15 + -1;
        r22 = r22 / 4;
        r0 = r22;
        r0 = r0 + -492;
        r22 = r0;
        r4 = r4 + r22;
        r22 = r15 + -1;
        r22 = r22 / 100;
        r22 = r22 + -19;
        r4 = r4 - r22;
        r22 = r15 + -1;
        r0 = r22;
        r0 = r0 / 400;
        r22 = r0;
        r22 = r22 + -4;
        r4 = r4 + r22;
        r5 = r14 - r4;
        r22 = r15 % 4;
        if (r22 != 0) goto L_0x006d;
    L_0x0069:
        r22 = r15 % 100;
        if (r22 != 0) goto L_0x0073;
    L_0x006d:
        r0 = r15 % 400;
        r22 = r0;
        if (r22 != 0) goto L_0x00f9;
    L_0x0073:
        r8 = 1;
    L_0x0074:
        if (r8 != 0) goto L_0x007c;
    L_0x0076:
        r22 = 365; // 0x16d float:5.11E-43 double:1.803E-321;
        r0 = r22;
        if (r5 < r0) goto L_0x0084;
    L_0x007c:
        if (r8 == 0) goto L_0x00fc;
    L_0x007e:
        r22 = 366; // 0x16e float:5.13E-43 double:1.81E-321;
        r0 = r22;
        if (r5 >= r0) goto L_0x00fc;
    L_0x0084:
        r22 = 59;
        r0 = r22;
        if (r5 < r0) goto L_0x0100;
    L_0x008a:
        if (r8 == 0) goto L_0x0100;
    L_0x008c:
        r22 = 13;
        r0 = r22;
        r11 = new int[r0];
        r11 = {-1, 0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335};
    L_0x0095:
        r0 = r11.length;
        r22 = r0;
        r7 = r22 + -1;
    L_0x009a:
        r22 = 1;
        r0 = r22;
        if (r7 < r0) goto L_0x00ad;
    L_0x00a0:
        r22 = r11[r7];
        r0 = r22;
        if (r5 < r0) goto L_0x010a;
    L_0x00a6:
        r10 = r7;
        r22 = r11[r7];
        r22 = r5 - r22;
        r3 = r22 + 1;
    L_0x00ad:
        r22 = new java.lang.StringBuilder;
        r22.<init>();
        r0 = r22;
        r22 = r0.append(r15);
        r23 = "-";
        r22 = r22.append(r23);
        r0 = r22;
        r22 = r0.append(r10);
        r23 = "-";
        r22 = r22.append(r23);
        r0 = r22;
        r22 = r0.append(r3);
        r23 = " ";
        r22 = r22.append(r23);
        r0 = r22;
        r22 = r0.append(r6);
        r23 = ":";
        r22 = r22.append(r23);
        r0 = r22;
        r22 = r0.append(r9);
        r23 = ":";
        r22 = r22.append(r23);
        r0 = r22;
        r22 = r0.append(r12);
        r22 = r22.toString();
        return r22;
    L_0x00f9:
        r8 = 0;
        goto L_0x0074;
    L_0x00fc:
        r15 = r15 + 1;
        goto L_0x003d;
    L_0x0100:
        r22 = 13;
        r0 = r22;
        r11 = new int[r0];
        r11 = {-1, 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        goto L_0x0095;
    L_0x010a:
        r7 = r7 + -1;
        goto L_0x009a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wwp.www.vrcpchaxun.utils.StringToTimeUtils.setTime2(long):java.lang.String");
    }

    public static String setToYearMonthDay(long time) {
        Date dat = new Date(System.currentTimeMillis() - time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        return new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5").format(gc.getTime());
    }
}