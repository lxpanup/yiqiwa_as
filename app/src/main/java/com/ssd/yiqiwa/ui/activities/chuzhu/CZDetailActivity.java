package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.widget.CirclePageIndicator;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GradientScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

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

    private List<?> homeBannerImages;

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
        homeBannerImages = new ArrayList(list);
        //简单使用
        detailBanner.setImages(homeBannerImages)
                .setImageLoader(new GlideImageLoader())
                .start();

        detailBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                photoShowDialog(position);
            }
        });
    }

    @Override
    public void destory() {

    }



    @OnClick({R.id.img_back,R.id.tv_cart,R.id.tv_buyer})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_cart:
                ToastUtils.showLong("添加购物车成功");
                break;
            case R.id.tv_buyer:
                startActivity(new Intent(activity,CartConfirmActivity.class));
                break;
        }
    }


    /**
     * 查看图片
     */
    private void photoShowDialog(int position){
        Dialog dia = new Dialog(activity, R.style.imageDialog);
        dia.setContentView(R.layout.item_image_banner);
        Banner banner =  dia.findViewById(R.id.banner);
//        uriList.remove(uriList.size()-1);
        banner.setImageLoader(new GlideImageLoader())
                .setImages(homeBannerImages)
                .isAutoPlay(false)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .start();
        banner.setIndicatorGravity(position);

        ImageView imageView = dia.findViewById(R.id.img_count_down);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

        Window window = dia.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0;
        window.setAttributes(layoutParams);
        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }
}
