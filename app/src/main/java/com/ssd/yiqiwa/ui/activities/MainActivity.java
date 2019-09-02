package com.ssd.yiqiwa.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPStaticUtils;
import com.jaeger.library.StatusBarUtil;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.custom.SpecialTab;
import com.ssd.yiqiwa.custom.SpecialTabRound;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.common.LoginActivity;
import com.ssd.yiqiwa.ui.adapter.ViewPagerAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.GlideLoadEngine;
import com.ssd.yiqiwa.widget.common.CommomDialog;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.PicassoEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;
//import com.ssd.yiqiwa.utils.StatusBarUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        viewPager.setOffscreenPageLimit(4);
        //设置消息数
        navigationController.setMessageNumber(1, 8);

        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(viewPager);

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
//        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
    }

    @Override
    public void destory() {

    }



    public void showCityList(TextView textView){
        CityPicker.from(MainActivity.this)
                .setHotCities(Constants.getHotCitys())
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        textView.setText(data.getName());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                CityPicker.from(MainActivity.this).locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
//                            }
//                        }, 3000);
                    }
                })
                .show();
    }




}
