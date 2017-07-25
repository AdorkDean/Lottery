package com.jz.lottery.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;

/**
 * Created by cheng on 2017/7/25.
 */


public class LogoActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGHT;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    String url;

    public LogoActivity() {
        this.SPLASH_DISPLAY_LENGHT = 2000;
        this.url = BuildConfig.VERSION_NAME;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_logo);
        this.url = getIntent().getStringExtra("url");
        this.mSharedPreferences = getSharedPreferences("phone", 0);
    }

    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent();
                intent.putExtra("url", LogoActivity.this.url);
                intent.setClass(LogoActivity.this, ShowUrlActivity.class);
                LogoActivity.this.startActivity(intent);
                LogoActivity.this.finish();
            }
        }, 2000);
    }
}