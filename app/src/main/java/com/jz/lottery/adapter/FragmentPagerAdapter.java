package com.jz.lottery.adapter;

/**
 * Created by cheng on 2017/7/25.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    private int currentIndex;
    private Class<?>[] fragmentClass;
    private Bundle[] mBundles;

    public FragmentPagerAdapter(FragmentManager fm, Class<?>[] fragmentClass) {
        super(fm);
        this.fragmentClass = fragmentClass;
    }

    public FragmentPagerAdapter(FragmentManager fm, Class<?>[] fragmentClass, Bundle[] mBundles) {
        super(fm);
        this.fragmentClass = fragmentClass;
        this.mBundles = mBundles;
    }

    public Fragment getItem(int arg0) {
        try {
            Fragment mFragment = (Fragment) this.fragmentClass[arg0].newInstance();
            if (this.mBundles == null || arg0 >= this.mBundles.length) {
                return mFragment;
            }
            mFragment.setArguments(this.mBundles[arg0]);
            return mFragment;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount() {
        return this.fragmentClass == null ? 0 : this.fragmentClass.length;
    }

    public void saveCurrentIndex(int index) {
        this.currentIndex = index;
    }

    public int getSaveCurrentIndex() {
        return this.currentIndex;
    }
}