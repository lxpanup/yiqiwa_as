package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;


import com.blankj.utilcode.util.CacheDiskStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.common.ForgetPwdActivity;
import com.ssd.yiqiwa.ui.activities.common.UpdatePwdActivity;
import com.ssd.yiqiwa.ui.adapter.HomeProductAdapter;
import com.ssd.yiqiwa.utils.CacheUtil;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;

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
            case R.id.mnbv_setting_02:
                startActivity(new Intent(SettingActivity.this, UpdatePwdActivity.class));
                break;
            case R.id.mnbv_setting_03:
                startActivity(new Intent(SettingActivity.this, ForgetPwdActivity.class));
                break;
            case R.id.mnbv_setting_04:
                startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
                break;
            case R.id.mnbv_setting_05:
                try {
                    String cacheStr = "缓存数量为:"+CacheUtil.getTotalCacheSize(SettingActivity.this)+" 是否确认清除缓存";
                    new AlertDialog.Builder(SettingActivity.this)
                        .setTitle("清除缓存")
                        .setMessage(cacheStr)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CacheDiskStaticUtils.clear();
                                ToastUtils.showLong("清除成功");
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mnbv_setting_06:
                GuanyuActivity.start(SettingActivity.this);
                break;
            case R.id.mnbv_setting_07:
                startActivity(new Intent(SettingActivity.this, UpdateUserActivity.class));
                break;
        }
    }
}
