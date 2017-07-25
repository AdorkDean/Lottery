package com.jz.lottery.newFragment;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jz.lottery.R;
import com.jz.lottery.activity.NoPassShowActivity;
import com.jz.lottery.activity.NoPassShowActivity.Entity;
import com.jz.lottery.base.BaseAdapter;
import com.jz.lottery.base.BaseFragment;

import java.util.ArrayList;


public class TwoFragment extends BaseFragment {
    private ArrayList<Entity> entities;
    ListView kaijiang_list;
    ListAdapter listAdapter;
    View view;

    public class ListAdapter extends BaseAdapter<Entity> {
        private RelativeLayout LiveRoomrl;
        private TextView courseclassbegin;
        private TextView coursename;
        private TextView coursestatus;
        private TextView line;
        private ArrayList<Entity> mData;
        private Context mcontext;
        private TextView qihao;
        private TextView time;
        private ImageView userimg;
        private TextView username;

        public ListAdapter(ArrayList<Entity> mData, Context mcontext) {
            super(mData, mcontext, R.layout.list_item);
            this.mcontext = mcontext;
            this.mData = mData;
        }

        public void setView(ViewHolder vh, Entity entity, int position) {
            this.time = (TextView) vh.findById(R.id.time);
            this.qihao = (TextView) vh.findById(R.id.qihao);
            this.userimg = (ImageView) vh.findById(R.id.user_img);
            this.username = (TextView) vh.findById(R.id.user_name);
            this.line = (TextView) vh.findById(R.id.line);
            this.coursename = (TextView) vh.findById(R.id.course_name);
            this.coursestatus = (TextView) vh.findById(R.id.course_status);
            this.courseclassbegin = (TextView) vh.findById(R.id.course_class_begin);
            this.LiveRoomrl = (RelativeLayout) vh.findById(R.id.LiveRoom_rl);
            this.userimg.setImageDrawable(TwoFragment.this.getResources().getDrawable(entity.getImg()));
            this.coursename.setText(entity.getName());
            this.coursestatus.setText(entity.getHaoma());
            this.qihao.setText(entity.getQishu());
            this.time.setText(entity.getTime());
        }
    }

    public TwoFragment() {
        this.entities = new ArrayList();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_two, container, false);
        return this.view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        this.entities.add(new Entity(R.drawable.chongqing, "\u91cd\u5e86\u65f6\u65f6\u5f69", "\u7b2c621431\u671f", "04 07 09 01 02 05 04", "3\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.sanfen, "\u4e09\u5206\u5f69", "\u7b2c20170603194\u671f", "6 3 5 7 9", "2\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.sanfenpk, "\u4e09\u5206pk\u62fe", "\u7b2c20170603324\u671f", "04 09 05 01 02 04 07", "5\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.shishicai, "\u5929\u6d25\u65f6\u65f6\u5f69", "\u7b2c20170603459\u671f", "07 04 05 08 09 02 01", "5\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.xingyun, "\u5e78\u8fd028", "\u7b2c20170603556\u671f", "6 3 5 1 7", "3\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.caipiao115, "11\u90095", "\u7b2c20170603524\u671f", "23 41 37 11 49", "4\u5206\u949f"));
        this.kaijiang_list = (ListView) this.view.findViewById(R.id.kaijiang_list);
        this.listAdapter = new ListAdapter(this.entities, getActivity());
        this.kaijiang_list.setAdapter(this.listAdapter);
    }
}