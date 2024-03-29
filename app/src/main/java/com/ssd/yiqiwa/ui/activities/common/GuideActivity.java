package com.ssd.yiqiwa.ui.activities.common;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GlideImageThisLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GuideActivity extends BaseActivity {
    @BindView(R.id.banner)
    public Banner banner;
    @BindView(R.id.btn_start)
    public AppCompatTextView btnStart;
    private List<Integer> images;

    @Override
    public Object offerLayout() {
        return R.layout.z_activity_guide;
    }

    @Override
    public void onBindView() {
        setNoTitleBarAndFullScreen();
        initBannerData();
        initViews();
    }

    private void initViews() {

        //简单使用
        banner.setImages(images);
        banner.setImageLoader(new GlideImageThisLoader());
        banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
        banner.setDelayTime(3000);

        banner.start();
//        banner.setImageLoader(new ModelImageLoader())
//                .setImages(images)
//                .isAutoPlay(false)
//                .start();
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                if (i == images.size() - 1) {
                    banner.stopAutoPlay();
                    banner.setViewPagerIsScroll(false);
                    btnStart.setVisibility(View.VISIBLE);
                    btnStart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuideActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                } else {
                    btnStart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }

    /**
     * 初始化banner数据
     */
    private void initBannerData() {
        images = new ArrayList<>();
        images.add(R.mipmap.launcher_01);
        images.add(R.mipmap.launcher_02);
        images.add(R.mipmap.launcher_03);
        images.add(R.mipmap.launcher_04);
        images.add(R.mipmap.launcher_05);
    }

    @Override
    public void destory() {
    }
}
