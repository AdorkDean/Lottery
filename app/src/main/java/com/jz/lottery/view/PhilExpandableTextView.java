package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.TextView;

public class PhilExpandableTextView extends TextView {
    private final int MAX;
    private boolean expandableStatus;
    private int lines;
    private PhilExpandableTextView mPhilTextView;

    public PhilExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.MAX = 1;
        this.expandableStatus = false;
        this.mPhilTextView = this;
        init();
    }

    private void init() {
        getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                PhilExpandableTextView.this.mPhilTextView.getViewTreeObserver().removeOnPreDrawListener(this);
                PhilExpandableTextView.this.lines = PhilExpandableTextView.this.getLineCount();
                return true;
            }
        });
        setMaxLines(1);
        setEllipsize(TruncateAt.END);
    }

    public void setExpandable(boolean isExpand) {
        if (isExpand) {
            setMaxLines(this.lines + 1);
        } else {
            setMaxLines(1);
        }
        this.expandableStatus = isExpand;
    }

    public boolean getExpandableStatus() {
        return this.expandableStatus;
    }
}