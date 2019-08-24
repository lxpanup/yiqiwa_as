package com.ssd.yiqiwa.ui.activities.other;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;

import butterknife.OnClick;


/**
 * 聊天页面
 */
public class MessageChatActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.activity_message_chat;
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
