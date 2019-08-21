package com.ssd.yiqiwa.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.ssd.yiqiwa.ui.fragment.NavCartFragment;
import com.ssd.yiqiwa.ui.fragment.NavMessageFragment;
import com.ssd.yiqiwa.ui.fragment.NavHomeFragment;
import com.ssd.yiqiwa.ui.fragment.NavMyFragment;
import com.ssd.yiqiwa.ui.fragment.NavPublishFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smile Wei
 * @since 2017/3/1.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(NavHomeFragment.newInstance());
        fragments.add(NavMessageFragment.newInstance());
        fragments.add(NavPublishFragment.newInstance());
        fragments.add(NavCartFragment.newInstance());
        fragments.add(NavMyFragment.newInstance());

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
