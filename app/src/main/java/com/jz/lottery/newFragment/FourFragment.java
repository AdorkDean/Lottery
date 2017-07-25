package com.jz.lottery.newFragment;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jz.lottery.R;
import com.jz.lottery.activity.LSHSuggestionFeedBackActivity;
import com.jz.lottery.base.BaseFragment;

public class FourFragment extends BaseFragment implements OnClickListener {
    RelativeLayout banbengengxin;
    RelativeLayout qingchuhuancun;
    TextView textview_cache;
    View view;
    RelativeLayout yijianfankui;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_four, container, false);
        return this.view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        this.qingchuhuancun = (RelativeLayout) this.view.findViewById(R.id.qingchuhuancun);
        this.textview_cache = (TextView) this.view.findViewById(R.id.textview_cache);
        this.banbengengxin = (RelativeLayout) this.view.findViewById(R.id.banbengengxin);
        this.yijianfankui = (RelativeLayout) this.view.findViewById(R.id.yijianfankui);
        this.qingchuhuancun.setOnClickListener(this);
        this.banbengengxin.setOnClickListener(this);
        this.yijianfankui.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qingchuhuancun /*2131427452*/:
                this.textview_cache.setText("0M");
                Toast.makeText(getContext(), "\u6e05\u9664\u6210\u529f", 0).show();
            case R.id.banbengengxin /*2131427454*/:
                Toast.makeText(getContext(), "\u5df2\u662f\u6700\u65b0\u7248\u672c", 0).show();
            case R.id.yijianfankui /*2131427456*/:
                startActivity(new Intent(getActivity(), LSHSuggestionFeedBackActivity.class));
            default:
        }
    }
}