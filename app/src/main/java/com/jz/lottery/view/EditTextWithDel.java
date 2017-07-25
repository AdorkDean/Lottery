package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.EditText;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;


public class EditTextWithDel extends EditText {
    private static final String TAG = "EditTextWithDel";
    private Drawable imgAble;
    private Drawable imgInable;
    private Context mContext;

    public EditTextWithDel(Context context) {
        super(context);
        this.mContext = context;
        setCompoundDrawablePadding(100);
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        setCompoundDrawablePadding(100);
        init();
    }

    public EditTextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setCompoundDrawablePadding(100);
        init();
    }

    private void init() {
        this.imgInable = this.mContext.getResources().getDrawable(R.drawable.guanbi3);
        this.imgAble = this.mContext.getResources().getDrawable(R.drawable.guanbi3);
        addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                EditTextWithDel.this.setDrawable();
            }
        });
        setDrawable();
    }

    private void setDrawable() {
        if (length() < 1) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else if (hasWindowFocus()) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, this.imgInable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        setDrawable();
        if (this.imgInable != null && event.getAction() == 1) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 50;
            if (rect.contains(eventX, eventY)) {
                setText(BuildConfig.VERSION_NAME);
            }
        }
        return super.onTouchEvent(event);
    }

    protected void finalize() throws Throwable {
        super.finalize();
    }
}