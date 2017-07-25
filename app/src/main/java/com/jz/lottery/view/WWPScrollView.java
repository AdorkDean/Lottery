package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class WWPScrollView extends ScrollView {
    View v1;
    View v2;

    public WWPScrollView(Context context) {
        super(context);
    }

    public WWPScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WWPScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setV1(View v1) {
        this.v1 = v1;
    }

    public void computeScroll() {
        if (getScrollY() >= this.v2.getTop()) {
            this.v1.setVisibility(0);
        } else {
            this.v1.setVisibility(8);
        }
        super.computeScroll();
    }
}