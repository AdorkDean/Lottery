package com.jz.lottery.view;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyListView extends LinearLayout {
    private BaseAdapter adapter;
    Context context;
    private MyOnItemClickListener onItemClickListener;

    /* renamed from: com.wwp.www.vrcpchaxun.view.MyListView.1 */
    class AnonymousClass1 implements OnClickListener {
        final /* synthetic */ int val$index;
        final /* synthetic */ LinearLayout val$layout;

        AnonymousClass1(LinearLayout linearLayout, int i) {
            this.val$layout = linearLayout;
            this.val$index = i;
        }

        public void onClick(View v) {
            if (MyListView.this.onItemClickListener != null) {
                MyListView.this.onItemClickListener.onItemClick(MyListView.this, this.val$layout, this.val$index, MyListView.this.adapter.getItem(this.val$index));
            }
        }
    }

    public interface MyOnItemClickListener {
        void onItemClick(ViewGroup viewGroup, View view, int i, Object obj);
    }

    public MyListView(Context context) {
        super(context);
        this.context = context;
        initAttr(null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttr(attrs);
    }

    public void notifyChange(boolean isAllreflash) {
        int count;
        if (isAllreflash) {
            count = 0;
            removeAllViews();
        } else {
            count = getChildCount();
        }
        LayoutParams params = new LayoutParams(-1, -2);
        if (this.adapter != null) {
            for (int i = count; i < this.adapter.getCount(); i++) {
                int index = i;
                LinearLayout layout = new LinearLayout(getContext());
                layout.setLayoutParams(params);
                layout.setOrientation(1);
                View v = this.adapter.getView(i, null, null);
                v.setOnClickListener(new AnonymousClass1(layout, index));
                ImageView imageView = new ImageView(getContext());
                layout.addView(v);
                layout.addView(imageView);
                addView(layout, index);
            }
        }
    }

    public void initAttr(AttributeSet attrs) {
        setOrientation(1);
    }

    public BaseAdapter getAdapter() {
        return this.adapter;
    }

    public void setAdapter(BaseAdapter adpater) {
        this.adapter = adpater;
        notifyChange(false);
    }

    public void setOnItemClickListener(MyOnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }
}