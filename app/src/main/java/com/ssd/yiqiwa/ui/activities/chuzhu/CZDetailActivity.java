package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;


import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.widget.CirclePageIndicator;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GradientScrollView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CZDetailActivity extends BaseActivity {

    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.detail_banner)
    Banner detailBanner;

    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_detail;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


        String[] urls = activity.getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        List<?> homeBannerImages = new ArrayList(list);
        //简单使用
        detailBanner.setImages(homeBannerImages)
                .setImageLoader(new GlideImageLoader())
                .start();


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
