package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ScrollView;

public class MyHalfHeightScrollview extends ScrollView {
    private Context mContext;

    public MyHalfHeightScrollview(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyHalfHeightScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyHalfHeightScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            Display display = ((Activity) this.mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(d.heightPixels / 2, MeasureSpec.UNSPECIFIED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}