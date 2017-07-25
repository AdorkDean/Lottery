package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MyPopupWindow extends PopupWindow {
    Button bt_popwindow_cancel;
    private View mMenuView;
    private TextView tv_sharefriend;
    private TextView tv_sharefriendcircle;

    public MyPopupWindow(Activity context, OnClickListener itemsOnclick) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        this.tv_sharefriend.setOnClickListener(itemsOnclick);
        this.tv_sharefriendcircle.setOnClickListener(itemsOnclick);
        this.bt_popwindow_cancel.setOnClickListener(itemsOnclick);
        setContentView(this.mMenuView);
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
    }
}