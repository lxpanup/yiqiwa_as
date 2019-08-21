package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import butterknife.OnClick;


public class UpdateUserActivity extends BaseActivity {


    @Override
    public Object offerLayout() {
        return R.layout.z_activity_updateuser;
    }

    @Override
    public void onBindView() {

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
