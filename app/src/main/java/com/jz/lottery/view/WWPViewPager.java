package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WWPViewPager extends ViewPager {
    private int current;
    private int height;
    private boolean scrollble;

    public WWPViewPager(Context context) {
        super(context);
        this.height = 0;
        this.scrollble = true;
    }

    public WWPViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.height = 0;
        this.scrollble = true;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() > this.current) {
            View child = getChildAt(this.current);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            this.height = child.getMeasuredHeight();
        }
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(this.height, MeasureSpec.EXACTLY));
    }


    public boolean onTouchEvent(MotionEvent ev) {
        if (this.scrollble) {
            return super.onTouchEvent(ev);
        }
        return true;
    }

    public boolean isScrollble() {
        return this.scrollble;
    }

    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }
}