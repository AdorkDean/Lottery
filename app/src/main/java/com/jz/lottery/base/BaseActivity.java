package com.jz.lottery.base;

import android.support.v7.app.AppCompatActivity;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cheng on 2017/7/25.
 */

public class BaseActivity extends AppCompatActivity {


    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }
}
