package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */


public class PasswordCharSequence implements CharSequence {
    private CharSequence mSource;

    public PasswordCharSequence(CharSequence source) {
        this.mSource = source;
    }

    public int length() {
        return this.mSource.length();
    }

    public char charAt(int index) {
        return '*';
    }

    public CharSequence subSequence(int start, int end) {
        return this.mSource.subSequence(start, end);
    }
}