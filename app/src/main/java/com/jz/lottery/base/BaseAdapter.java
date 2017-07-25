package com.jz.lottery.base;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
    private List<T> mData;
    private int mResLayoutId;
    private Context mcontext;
    public ViewHolder vh;

    public class ViewHolder {
        private Map<Integer, View> mViews;
        private View v;

        public Map<Integer, View> getmViews() {
            return this.mViews;
        }

        public ViewHolder(View v) {
            this.mViews = new HashMap();
            this.v = v;
        }

        public View findById(int id) {
            View vs = this.v.findViewById(id);
            this.mViews.put(Integer.valueOf(id), vs);
            return vs;
        }
    }

    public abstract void setView(ViewHolder viewHolder, T t, int i);

    public BaseAdapter(List<T> mData, Context mcontext, int mResLayoutId) {
        this.mData = mData;
        this.mcontext = mcontext;
        this.mResLayoutId = mResLayoutId;
    }

    public List<T> getmData() {
        return this.mData;
    }

    public void setmData(List<T> mData) {
        this.mData = mData;
    }

    public int getCount() {
        if (this.mData != null) {
            return this.mData.size();
        }
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(this.mcontext, this.mResLayoutId, null);
            this.vh = new ViewHolder(convertView);
            convertView.setTag(this.vh);
        }
        setView((ViewHolder) convertView.getTag(), this.mData.get(position), position);
        return convertView;
    }

    public Object getItem(int position) {
        return this.mData.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }
}