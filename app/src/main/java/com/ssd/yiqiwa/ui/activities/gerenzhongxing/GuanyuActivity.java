package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import butterknife.OnClick;


/**
 * 关于
 */
public class GuanyuActivity extends BaseActivity {


    public static void start(Context context){
        Intent intent=new Intent(context, GuanyuActivity.class);
        context.startActivity(intent);
    }



    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_guanyu;
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
