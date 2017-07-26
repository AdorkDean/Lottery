package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.newActivity.FragmentVPActivity;

public class NoPassActivity extends BaseActivity {
    TextView enter;
    private int skipCount = 5;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            enter.setText(getString(R.string.skip_hint,skipCount));
            if (skipCount == 0) {
                NoPassActivity.this.startActivity(new Intent(NoPassActivity.this, FragmentVPActivity.class));
                NoPassActivity.this.finish();
            }else
                handler.sendEmptyMessageDelayed(0,1000);
            skipCount -=1;
        }
    };

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
        handler.sendEmptyMessageDelayed(0,1000);
    }
}