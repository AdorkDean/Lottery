package com.jz.lottery.newActivity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

import com.jz.lottery.R;
import com.jz.lottery.adapter.FragmentPagerAdapter;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.newFragment.FirstFragment;
import com.jz.lottery.newFragment.FourFragment;
import com.jz.lottery.newFragment.ThreeFragment;
import com.jz.lottery.newFragment.TwoFragment;
import com.jz.lottery.view.DrawableCenterButton;

public class FragmentVPActivity extends BaseActivity implements OnClickListener {
    FragmentPagerAdapter fragmnetpageradapter;
    private DrawableCenterButton mainradiohome;
    private DrawableCenterButton mainradiosearch;
    private DrawableCenterButton mainradioself;
    private DrawableCenterButton mainradiozzzs;
    private ViewPager viewPager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_fragment_vp);
        initView();
    }

    private void initView() {
        this.viewPager = (ViewPager) findViewById(R.id.packpage_vPager);
        this.mainradiohome = (DrawableCenterButton) findViewById(R.id.main_radio_home);
        this.mainradiozzzs = (DrawableCenterButton) findViewById(R.id.main_radio_zzzs);
        this.mainradiosearch = (DrawableCenterButton) findViewById(R.id.main_radio_search);
        this.mainradioself = (DrawableCenterButton) findViewById(R.id.main_radio_self);
        this.mainradiohome.setOnClickListener(this);
        this.mainradiozzzs.setOnClickListener(this);
        this.mainradiosearch.setOnClickListener(this);
        this.mainradioself.setOnClickListener(this);
        this.fragmnetpageradapter = new FragmentPagerAdapter(getSupportFragmentManager(), new Class[]{FirstFragment.class, TwoFragment.class, ThreeFragment.class, FourFragment.class}, new Bundle[0]);
        this.viewPager.setAdapter(this.fragmnetpageradapter);
        this.viewPager.setOffscreenPageLimit(4);
        this.viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mainradiohome.setChecked(true);
                        break;
                    case 1:
                        mainradiozzzs.setChecked(true);
                        break;
                    case 2:
                        mainradiosearch.setChecked(true);
                        break;
                    case 3:
                        mainradioself.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_radio_home:
                this.viewPager.setCurrentItem(0);
                break;
            case R.id.main_radio_zzzs:
            this.viewPager.setCurrentItem(1);
                break;
            case R.id.main_radio_search:
            this.viewPager.setCurrentItem(2);
                break;
            case R.id.main_radio_self:
            this.viewPager.setCurrentItem(3);
                break;
            default:
        }
    }
}