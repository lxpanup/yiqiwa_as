package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CZShaixuanActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_shaixuan;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


    }

    @Override
    public void destory() {

    }


    @OnClick({R.id.img_back})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }


}
