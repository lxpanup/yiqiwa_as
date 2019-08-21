package com.ssd.yiqiwa.ui.activities.other;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.CartConfirmAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 */
public class CartConfirmActivity extends BaseActivity {


    @BindView(R.id.recy_cart_confirm)
    RecyclerView recy_cart_confirm;

    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.activity_cart_confirm;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recy_cart_confirm.setLayoutManager(new LinearLayoutManager(activity));
        recy_cart_confirm.setAdapter(new CartConfirmAdapter(activity,list));

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
