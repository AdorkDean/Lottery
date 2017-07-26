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
import android.widget.TextView;

import com.jz.lottery.R;
import com.jz.lottery.base.BaseAdapter;
import com.jz.lottery.base.BaseFragment;
import com.jz.lottery.entity.LotteryEntity;

import java.util.ArrayList;


public class TwoFragment extends BaseFragment {
    private ArrayList<LotteryEntity> entities;
    ListView kaijiang_list;
    ListAdapter listAdapter;
    View view;



    public class ListAdapter extends BaseAdapter<LotteryEntity> {
        private TextView coursename;
        private TextView coursestatus;
        private TextView qihao;
        private TextView time;
        private ImageView userimg;

        public ListAdapter(ArrayList<LotteryEntity> mData, Context mcontext) {
            super(mData, mcontext, R.layout.list_item);
        }

        public void setView(ViewHolder vh, LotteryEntity entity, int position) {
            this.qihao = (TextView) vh.findById(R.id.qihao);
            this.userimg = (ImageView) vh.findById(R.id.user_img);
            this.coursename = (TextView) vh.findById(R.id.course_name);
            this.coursestatus = (TextView) vh.findById(R.id.course_status);
            this.userimg.setImageDrawable(TwoFragment.this.getResources().getDrawable(entity.getImg()));
            this.coursename.setText(entity.getName());
            this.coursestatus.setText(entity.getHaoma());
            this.qihao.setText(entity.getQishu());
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
        this.entities.add(new LotteryEntity(R.drawable.chongqing, "重庆时时彩", "\u7b2c621431\u671f", "04 07 09 01 02 05 04", "3\u5206\u949f"));
        this.entities.add(new LotteryEntity(R.drawable.sanfen, "三分彩", "\u7b2c20170603194\u671f", "6 3 5 7 9", "2\u5206\u949f"));
        this.entities.add(new LotteryEntity(R.drawable.sanfenpk, "三分pk10", "\u7b2c20170603324\u671f", "04 09 05 01 02 04 07", "5\u5206\u949f"));
        this.entities.add(new LotteryEntity(R.drawable.shishicai, "天津时时彩", "\u7b2c20170603459\u671f", "07 04 05 08 09 02 01", "5\u5206\u949f"));
        this.entities.add(new LotteryEntity(R.drawable.xingyun, "幸运28", "\u7b2c20170603556\u671f", "6 3 5 1 7", "3\u5206\u949f"));
        this.entities.add(new LotteryEntity(R.drawable.caipiao115, "11选5", "\u7b2c20170603524\u671f", "23 41 37 11 49", "4\u5206\u949f"));
        this.kaijiang_list = (ListView) this.view.findViewById(R.id.kaijiang_list);
        this.listAdapter = new ListAdapter(this.entities, getActivity());
        this.kaijiang_list.setAdapter(this.listAdapter);
    }
}