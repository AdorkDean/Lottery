package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.webkit.WebView;

public class MyWebview extends WebView {
    PlayFinish df;

    public interface PlayFinish {
        void After();
    }

    public void setDf(PlayFinish playFinish) {
        this.df = playFinish;
    }

    public MyWebview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyWebview(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.df.After();
    }
}