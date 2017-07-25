package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import rx.internal.operators.OnSubscribeConcatMap;

public class MyNestedScrollView extends NestedScrollView {
    private static final String tag = "MyNestedScrollView";
    private Handler handler;
    private OnScrollListener onScrollListener;
    OnTouchListener onTouchListener;
    private View view;

    public interface OnScrollListener {
        void onBottom();

        void onScroll();

        void onTop();
    }

    public MyNestedScrollView(Context context) {
        super(context);
        this.onTouchListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case OnSubscribeConcatMap.BOUNDARY /*1*/:
                        if (!(MyNestedScrollView.this.view == null || MyNestedScrollView.this.onScrollListener == null)) {
                            MyNestedScrollView.this.handler.sendMessageDelayed(MyNestedScrollView.this.handler.obtainMessage(1), 20);
                            break;
                        }
                }
                return false;
            }
        };
    }

    public MyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.onTouchListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case OnSubscribeConcatMap.BOUNDARY /*1*/:
                        if (!(MyNestedScrollView.this.view == null || MyNestedScrollView.this.onScrollListener == null)) {
                            MyNestedScrollView.this.handler.sendMessageDelayed(MyNestedScrollView.this.handler.obtainMessage(1), 20);
                            break;
                        }
                }
                return false;
            }
        };
    }

    public MyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.onTouchListener = new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case OnSubscribeConcatMap.BOUNDARY /*1*/:
                        if (!(MyNestedScrollView.this.view == null || MyNestedScrollView.this.onScrollListener == null)) {
                            MyNestedScrollView.this.handler.sendMessageDelayed(MyNestedScrollView.this.handler.obtainMessage(1), 20);
                            break;
                        }
                }
                return false;
            }
        };
    }

    private void init() {
        setOnTouchListener(this.onTouchListener);
        this.handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case OnSubscribeConcatMap.BOUNDARY /*1*/:
                        if (MyNestedScrollView.this.view.getMeasuredHeight() <= MyNestedScrollView.this.getScrollY() + MyNestedScrollView.this.getHeight()) {
                            if (MyNestedScrollView.this.onScrollListener != null) {
                                MyNestedScrollView.this.onScrollListener.onBottom();
                            }
                        } else if (MyNestedScrollView.this.getScrollY() == 0) {
                            if (MyNestedScrollView.this.onScrollListener != null) {
                                MyNestedScrollView.this.onScrollListener.onTop();
                            }
                        } else if (MyNestedScrollView.this.onScrollListener != null) {
                            MyNestedScrollView.this.onScrollListener.onScroll();
                        }
                    default:
                }
            }
        };
    }

    public void getView() {
        this.view = getChildAt(0);
        if (this.view != null) {
            init();
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }
}