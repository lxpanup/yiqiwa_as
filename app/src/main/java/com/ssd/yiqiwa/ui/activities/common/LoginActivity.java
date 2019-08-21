package com.ssd.yiqiwa.ui.activities.common;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.utils.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity  {


    @BindView(R.id.tbs_login)
    TabLayout tbs_login;
    @BindView(R.id.rlt_logo_show)
    RelativeLayout rlt_logo_show;
    @BindView(R.id.lil_login_register)
    LinearLayout lil_login_register;


    @Override
    public Object offerLayout() {
        return R.layout.z_activity_login;
    }

    @Override
    public void onBindView() {

//        hideActionBar();
        //根据字体长度
        tbs_login.setTabIndicatorFullWidth(false);

        tbs_login.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    rlt_logo_show.setVisibility(View.VISIBLE);
                    lil_login_register.setVisibility(View.GONE);
                }else{
                    rlt_logo_show.setVisibility(View.GONE);
                    lil_login_register.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void destory() {

    }

    @OnClick({R.id.txt_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login:
                getLogin();
                break;
        }

    }



    private  void getLogin(){
        showToast("请求成功");

    }

}
