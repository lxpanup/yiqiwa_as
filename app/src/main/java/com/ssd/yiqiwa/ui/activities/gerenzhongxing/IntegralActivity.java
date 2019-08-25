package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.fragment.gerenzhongxing.IntegralFragment;
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 我的积分
 */
public class IntegralActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @BindView(R.id.view_pager_integral)
    ViewPager mViewPager;
    @BindView(R.id.tab_integral)
    TabLayout myTabLayout;


    public static void start(Context context){
        Intent intent=new Intent(context, IntegralActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_integral;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


        FragmentTabAdapter mMyAdapter = new FragmentTabAdapter(getSupportFragmentManager(),new String[]{"所有","收入","体现"});
        mMyAdapter.addFragment(new IntegralFragment());
        mMyAdapter.addFragment(new IntegralFragment());
        mMyAdapter.addFragment(new IntegralFragment());
        mViewPager.setAdapter(mMyAdapter);

        myTabLayout.setupWithViewPager(mViewPager);
        myTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;

        }
    }







}
