package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class DrawableCenterButton extends RadioButton {
    public DrawableCenterButton(Context context) {
        super(context);
    }

    public DrawableCenterButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public DrawableCenterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            Drawable drawableLeft = drawables[2];
            if (drawableLeft != null) {
                float bodyWidth = (((float) drawableLeft.getIntrinsicWidth()) + getPaint().measureText(getText().toString())) + ((float) getCompoundDrawablePadding());
                setPadding(0, 0, (int) (((float) getWidth()) - bodyWidth), 0);
                canvas.translate((((float) getWidth()) - bodyWidth) / 2.0f, 0.0f);
            }
        }
        super.onDraw(canvas);
    }
}