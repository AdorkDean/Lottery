package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jz.lottery.R;


public class SlideSwitchButton extends View {
    public static final int COLOR_THEME = Color.parseColor("#ff666666");
    private static final int RIM_SIZE = 3;
    public static final int SHAPE_CIRCLE = 2;
    public static final int SHAPE_RECT = 1;
    private int alpha;
    private Rect backRect;
    private int color_theme;
    private int diffX;
    private int eventLastX;
    private int eventStartX;
    private Rect frontRect;
    private int frontRect_left;
    private int frontRect_left_begin;
    private boolean isOpen;
    private SlideListener listener;
    private int max_left;
    private int min_left;
    private Paint paint;
    private int shape;

    /* renamed from: com.wwp.www.vrcpchaxun.view.SlideSwitchButton.2 */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ boolean val$toRight;

        AnonymousClass2(boolean z, Handler handler) {
            this.val$toRight = z;
            this.val$handler = handler;
        }

        public void run() {
            if (this.val$toRight) {
                while (SlideSwitchButton.this.frontRect_left <= SlideSwitchButton.this.max_left) {
                    SlideSwitchButton.this.alpha = (int) ((((float) SlideSwitchButton.this.frontRect_left) * 255.0f) / ((float) SlideSwitchButton.this.max_left));
                    SlideSwitchButton.this.invalidateView();
                    SlideSwitchButton.this.frontRect_left = SlideSwitchButton.this.frontRect_left + SlideSwitchButton.RIM_SIZE;
                    try {
                        Thread.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SlideSwitchButton.this.alpha = MotionEventCompat.ACTION_MASK;
                SlideSwitchButton.this.frontRect_left = SlideSwitchButton.this.max_left;
                SlideSwitchButton.this.isOpen = true;
                if (SlideSwitchButton.this.listener != null) {
                    this.val$handler.sendEmptyMessage(SlideSwitchButton.SHAPE_RECT);
                }
                SlideSwitchButton.this.frontRect_left_begin = SlideSwitchButton.this.max_left;
                return;
            }
            while (SlideSwitchButton.this.frontRect_left >= SlideSwitchButton.this.min_left) {
                SlideSwitchButton.this.alpha = (int) ((((float) SlideSwitchButton.this.frontRect_left) * 255.0f) / ((float) SlideSwitchButton.this.max_left));
                SlideSwitchButton.this.invalidateView();
                SlideSwitchButton.this.frontRect_left = SlideSwitchButton.this.frontRect_left - 3;
                try {
                    Thread.sleep(3);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            SlideSwitchButton.this.alpha = SlideSwitchButton.COLOR_THEME;
            SlideSwitchButton.this.frontRect_left = SlideSwitchButton.this.min_left;
            SlideSwitchButton.this.isOpen = false;
            if (SlideSwitchButton.this.listener != null) {
                this.val$handler.sendEmptyMessage(SlideSwitchButton.COLOR_THEME);
            }
            SlideSwitchButton.this.frontRect_left_begin = SlideSwitchButton.this.min_left;
        }
    }

    public interface SlideListener {
        void close();

        void open();
    }


    public SlideSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.frontRect_left_begin = RIM_SIZE;
        this.diffX = 0;
        this.listener = null;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.slideswitch);
        this.color_theme = a.getColor(R.styleable.slideswitch_color_theme, COLOR_THEME);
        this.isOpen = a.getBoolean(R.styleable.slideswitch_shape_circle, false);
        this.shape = a.getInt(R.styleable.slideswitch_shape_rect, SHAPE_RECT);
        a.recycle();
    }

    public SlideSwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, COLOR_THEME);
    }

    public SlideSwitchButton(Context context) {
        this(context, null);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(280, widthMeasureSpec);
        int height = measureDimension(140, heightMeasureSpec);
        if (this.shape == SHAPE_CIRCLE && width < height) {
            width = height * SHAPE_CIRCLE;
        }
        setMeasuredDimension(width, height);
        initDrawingVal();
    }

    public void initDrawingVal() {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        this.backRect = new Rect(COLOR_THEME, COLOR_THEME, width, height);
        this.min_left = RIM_SIZE;
        if (this.shape == SHAPE_RECT) {
            this.max_left = width / SHAPE_CIRCLE;
        } else {
            this.max_left = (width - (height - 6)) - 3;
        }
        if (this.isOpen) {
            this.frontRect_left = this.max_left;
            this.alpha = MotionEventCompat.ACTION_MASK;
        } else {
            this.frontRect_left = RIM_SIZE;
            this.alpha = COLOR_THEME;
        }
        this.frontRect_left_begin = this.frontRect_left;
    }

    public int measureDimension(int defaultSize, int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            return specSize;
        }
        int result = defaultSize;
        if (specMode == MeasureSpec.UNSPECIFIED) {
            return Math.min(result, specSize);
        }
        return result;
    }

    protected void onDraw(Canvas canvas) {
        if (this.shape == SHAPE_RECT) {
            this.paint.setColor(getResources().getColor(R.color.color_ff0000));
            canvas.drawRect(this.backRect, this.paint);
            this.paint.setColor(this.color_theme);
            this.paint.setAlpha(this.alpha);
            canvas.drawRect(this.backRect, this.paint);
            this.frontRect = new Rect(this.frontRect_left, RIM_SIZE, (this.frontRect_left + (getMeasuredWidth() / SHAPE_CIRCLE)) - 3, getMeasuredHeight() - 3);
            this.paint.setColor(-1);
            canvas.drawRect(this.frontRect, this.paint);
            return;
        }
        int radius = ((int) (((double) this.backRect.height()) / 1.8d)) - 3;
        this.paint.setColor(getResources().getColor(R.color.color_ff0000));
        canvas.drawRoundRect(new RectF(this.backRect), (float) radius, (float) radius, this.paint);
        this.paint.setColor(this.color_theme);
        this.paint.setAlpha(this.alpha);
        canvas.drawRoundRect(new RectF(this.backRect), (float) radius, (float) radius, this.paint);
        this.frontRect = new Rect(this.frontRect_left, RIM_SIZE, (this.frontRect_left + this.backRect.height()) - 6, this.backRect.height() - 3);
        this.paint.setColor(-1);
        canvas.drawRoundRect(new RectF(this.frontRect), (float) radius, (float) radius, this.paint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                this.eventStartX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_UP /*1*/:
                boolean toRight;
                int wholeX = (int) (event.getRawX() - ((float) this.eventStartX));
                this.frontRect_left_begin = this.frontRect_left;
                if (this.frontRect_left_begin > this.max_left / SHAPE_CIRCLE) {
                    toRight = true;
                } else {
                    toRight = false;
                }
                if (Math.abs(wholeX) < RIM_SIZE) {
                    if (toRight) {
                        toRight = false;
                    } else {
                        toRight = true;
                    }
                }
                moveToDest(toRight);
                break;
            case MotionEvent.ACTION_MOVE /*2*/:
                this.eventLastX = (int) event.getRawX();
                this.diffX = this.eventLastX - this.eventStartX;
                int tempX = this.diffX + this.frontRect_left_begin;
                if (tempX > this.max_left) {
                    tempX = this.max_left;
                }
                if (tempX < this.min_left) {
                    tempX = this.min_left;
                }
                if (tempX >= this.min_left && tempX <= this.max_left) {
                    this.frontRect_left = tempX;
                    this.alpha = (int) ((255.0f * ((float) tempX)) / ((float) this.max_left));
                    invalidateView();
                    break;
                }
        }
        return true;
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public void setSlideListener(SlideListener listener) {
        this.listener = listener;
    }

    public void moveToDest(boolean toRight) {
        new Thread(new AnonymousClass2(toRight, new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == SlideSwitchButton.SHAPE_RECT) {
                    SlideSwitchButton.this.listener.open();
                } else {
                    SlideSwitchButton.this.listener.close();
                }
            }
        })).start();
    }

    public void setState(boolean isOpen) {
        this.isOpen = isOpen;
        initDrawingVal();
        invalidateView();
        if (this.listener == null) {
            return;
        }
        if (isOpen) {
            this.listener.open();
        } else {
            this.listener.close();
        }
    }

    public void setShapeType(int shapeType) {
        this.shape = shapeType;
    }
}