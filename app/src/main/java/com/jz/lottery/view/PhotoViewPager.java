package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class PhotoViewPager extends ViewPager {
    private static final float SCALE_MAX = 0.5f;
    private HashMap<Integer, View> mChildrenViews;
    private View mLeft;
    private View mRight;
    private float mScale;
    private float mTrans;

    public PhotoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mChildrenViews = new LinkedHashMap();
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float effectOffset;
        if (isSmall(positionOffset)) {
            effectOffset = 0.0f;
        } else {
            effectOffset = positionOffset;
        }
        this.mLeft = findViewFromObject(position);
        this.mRight = findViewFromObject(position + 1);
        animateStack(this.mLeft, this.mRight, effectOffset, positionOffsetPixels);
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    public void setObjectForPosition(View view, int position) {
        this.mChildrenViews.put(Integer.valueOf(position), view);
    }

    public View findViewFromObject(int position) {
        return (View) this.mChildrenViews.get(Integer.valueOf(position));
    }

    private boolean isSmall(float positionOffset) {
        return ((double) Math.abs(positionOffset)) < 1.0E-4d;
    }

    protected void animateStack(View left, View right, float effectOffset, int positionOffsetPixels) {
        if (right != null) {
            this.mScale = (SCALE_MAX * effectOffset) + SCALE_MAX;
            this.mTrans = (float) (((-getWidth()) - getPageMargin()) + positionOffsetPixels);
            right.setScaleX(this.mScale);
            right.setScaleY(this.mScale);
            right.setTranslationX(this.mTrans);
        }
        if (left != null) {
            left.bringToFront();
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}