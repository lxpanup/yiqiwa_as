package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.HomeProductAdapter;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {


    @Override
    public Object offerLayout() {
        return R.layout.z_activity_setting;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void destory() {

    }


    @OnClick({R.id.img_back,R.id.mnbv_setting_01,R.id.mnbv_setting_02,R.id.mnbv_setting_03,R.id.mnbv_setting_04
            ,R.id.mnbv_setting_05,R.id.mnbv_setting_06,R.id.mnbv_setting_07})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.mnbv_setting_01:
                startActivity(new Intent(SettingActivity.this, UpdateUserActivity.class));
                break;
            case R.id.mnbv_setting_07:
                startActivity(new Intent(SettingActivity.this, UpdateUserActivity.class));
                break;
        }
    }
}
