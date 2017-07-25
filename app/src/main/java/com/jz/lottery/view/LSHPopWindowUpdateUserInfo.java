package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jz.lottery.R;


public class LSHPopWindowUpdateUserInfo extends PopupWindow {
    private Context context;
    private TextView mTextView;

    public LSHPopWindowUpdateUserInfo(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LSHPopWindowUpdateUserInfo(Context context) {
        super(context);
        this.context = context;
        setContentView(setLayout());
        setTouchInterceptor(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        setBackgroundDrawable(new ColorDrawable(1711276032));
    }

    private View setLayout() {
        View view = View.inflate(this.context, R.layout.lsh_popwindow_update_user_info, null);
        this.mTextView = (TextView) view.findViewById(R.id.LSHProductVideoDetailAc_progressBar1_tv);
        return view;
    }

    public void popWindowShow(View v) {
        showAtLocation(v, 17, 0, 0);
    }

    public void setTextViewText(String str) {
        if (this.mTextView != null) {
            this.mTextView.setText(str);
        }
    }
}