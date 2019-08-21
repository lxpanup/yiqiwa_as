package com.ssd.yiqiwa.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.custom.SpecialTab;
import com.ssd.yiqiwa.custom.SpecialTabRound;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.MyViewPagerAdapter;
import com.ssd.yiqiwa.ui.adapter.ViewPagerAdapter;
//import com.ssd.yiqiwa.utils.StatusBarUtil;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab)
    PageNavigationView tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fake_status_bar)
    View fakeStatusbar;

    @Override
    public Object offerLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onBindView() {

        NavigationController navigationController = tab.custom()
                .addItem(newItem(R.mipmap.ic_main_home_in,R.mipmap.ic_main_home_on,"首页"))
                .addItem(newItem(R.mipmap.ic_main_message_in,R.mipmap.ic_main_message_on,"消息"))
                .addItem(newRoundItem(R.mipmap.ic_main_fabu,R.mipmap.ic_main_fabu,"发布"))
                .addItem(newItem(R.mipmap.ic_main_cart_in,R.mipmap.ic_main_cart_on,"购物车"))
                .addItem(newItem(R.mipmap.ic_main_my_in,R.mipmap.ic_main_my_on,"我的"))
                .build();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


        //设置消息数
        navigationController.setMessageNumber(1, 8);

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        StatusBarUtil.setTransparentForImageViewInFragment(MainActivity.this,fakeStatusbar);

    }

    /**
     * 正常tab
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text){
        SpecialTab mainTab = new SpecialTab(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }

    /**
     * 圆形tab
     */
    private BaseTabItem newRoundItem(int drawable,int checkedDrawable,String text){
        SpecialTabRound mainTab = new SpecialTabRound(this);
        mainTab.initialize(drawable,checkedDrawable,text);
        mainTab.setTextDefaultColor(0xFF888888);
        mainTab.setTextCheckedColor(0xFF009688);
        return mainTab;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }

    @Override
    public void destory() {

    }
}
