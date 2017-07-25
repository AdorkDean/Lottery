package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

public class StringFormatUtil {
    private int color;
    private int end;
    private String highlightStr;
    private Context mContext;
    private SpannableStringBuilder spBuilder;
    private int start;
    private String wholeStr;

    public StringFormatUtil(Context context, String wholeStr, String highlightStr, int color) {
        this.start = 0;
        this.end = 0;
        this.mContext = context;
        this.wholeStr = wholeStr;
        this.highlightStr = highlightStr;
        this.color = color;
    }

    public StringFormatUtil fillColor() {
        if (TextUtils.isEmpty(this.wholeStr) || TextUtils.isEmpty(this.highlightStr)) {
            return null;
        }
        if (!this.wholeStr.contains(this.highlightStr)) {
            return null;
        }
        this.start = this.wholeStr.indexOf(this.highlightStr);
        this.end = this.start + this.highlightStr.length();
        this.spBuilder = new SpannableStringBuilder(this.wholeStr);
        this.color = this.mContext.getResources().getColor(this.color);
        this.spBuilder.setSpan(new ForegroundColorSpan(this.color), this.start, this.end, 33);
        return this;
    }

    public SpannableStringBuilder getResult() {
        if (this.spBuilder != null) {
            return this.spBuilder;
        }
        return null;
    }
}