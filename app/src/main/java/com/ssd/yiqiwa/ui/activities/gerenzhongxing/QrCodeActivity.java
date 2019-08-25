package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import butterknife.OnClick;


/**
 * 二维码
 */
public class QrCodeActivity extends BaseActivity {


    public static void start(Context context){
        Intent intent=new Intent(context, QrCodeActivity.class);
        context.startActivity(intent);
    }



    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_qrcode;
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
