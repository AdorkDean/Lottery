package com.jz.lottery.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;

import java.util.ArrayList;

/**
 * Created by cheng on 2017/7/25.
 */

public class GuideViewActivity extends BaseActivity {


    private static final int[] pics;
    private Button mButton;
    private int mIntCurrentNum;
    private ArrayList<String> mList;
    private ViewPager mViewPager;
    private GuideViewAdapter mVpAdapter;
    private ArrayList<Integer> showData;
    private String url;

    public class GuideViewAdapter extends PagerAdapter {
        private Context c;
        private ArrayList<Integer> datas;
        private LayoutInflater inflater;

        public GuideViewAdapter(Context c, ArrayList<Integer> datas) {
            this.c = c;
            this.datas = datas;
            this.inflater = LayoutInflater.from(c);
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public int getCount() {
            return this.datas.size();
        }

        public Object instantiateItem(ViewGroup container, int position) {
            View v = this.inflater.inflate(R.layout.activity_guideview_viewpager_imgv, container, false);
            ((ImageView) v.findViewById(R.id.guideview_vp_imgv)).setImageResource(((Integer) this.datas.get(position)).intValue());
            container.addView(v);
            return v;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    public GuideViewActivity() {
        this.mIntCurrentNum = 0;
        this.mList = new ArrayList();
        this.showData = new ArrayList();
        this.url = BuildConfig.VERSION_NAME;
    }

    static {
        pics = new int[]{R.drawable.yindao1, R.drawable.yindao2, R.drawable.yindao3, R.drawable.yindao4};
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_guide_view);
        this.url = getIntent().getStringExtra("url");
        initView();
    }

    private void initView() {
        this.mButton = (Button) findViewById(R.id.button);
        this.mViewPager = (ViewPager) findViewById(R.id.viewpager);
        this.showData.add(Integer.valueOf(R.drawable.yindao1));
        this.showData.add(Integer.valueOf(R.drawable.yindao2));
        this.showData.add(Integer.valueOf(R.drawable.yindao3));
        this.showData.add(Integer.valueOf(R.drawable.yindao4));
        this.mVpAdapter = new GuideViewAdapter(getApplicationContext(), this.showData);
        this.mViewPager.setAdapter(this.mVpAdapter);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset == 0.0f && position == GuideViewActivity.pics.length - 1) {
                    GuideViewActivity.this.mButton.setVisibility(0);
                } else {
                    GuideViewActivity.this.mButton.setVisibility(8);
                }
            }

            public void onPageSelected(int arg0) {
                GuideViewActivity.this.mIntCurrentNum = arg0;
                GuideViewActivity.this.mViewPager.setCurrentItem(GuideViewActivity.this.mIntCurrentNum);
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
        this.mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("url", GuideViewActivity.this.url);
                intent.setClass(GuideViewActivity.this, ShowUrlActivity.class);
                GuideViewActivity.this.startActivity(intent);
                GuideViewActivity.this.finish();
            }
        });
    }
}
