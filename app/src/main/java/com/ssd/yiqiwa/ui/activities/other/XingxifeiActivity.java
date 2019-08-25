package com.ssd.yiqiwa.ui.activities.other;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import butterknife.OnClick;


/**
 * 信息费
 */
public class XingxifeiActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.activity_xingxifei;
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
