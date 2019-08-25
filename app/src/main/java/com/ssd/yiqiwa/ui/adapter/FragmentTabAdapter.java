package com.ssd.yiqiwa.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();
    private String[] titles;

    public FragmentTabAdapter(FragmentManager fm,String [] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    public void addFragment(Fragment fragment){
        fragments.add(fragment);
    }
}
