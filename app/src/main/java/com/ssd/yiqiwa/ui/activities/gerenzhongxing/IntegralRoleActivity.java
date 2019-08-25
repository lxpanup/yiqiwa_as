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

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 积分规则
 */
public class IntegralRoleActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    public static void start(Context context){
        Intent intent=new Intent(context, IntegralRoleActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_integralrole;
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
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;

        }
    }







}
