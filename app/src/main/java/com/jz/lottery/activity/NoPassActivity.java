package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.newActivity.FragmentVPActivity;

public class NoPassActivity extends BaseActivity {
    TextView enter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_no_pass);
        this.enter = (TextView) findViewById(R.id.enter);
        this.enter.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                NoPassActivity.this.startActivity(new Intent(NoPassActivity.this, FragmentVPActivity.class));
                NoPassActivity.this.finish();
            }
        });
    }
}