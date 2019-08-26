package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.GongzuojingliAdapter;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 我的简历
 */
public class MyJianliActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @BindView(R.id.recy_jianli_list)
    RecyclerView myReclerView;



    public static void start(Context context){
        Intent intent=new Intent(context, MyJianliActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_myjianli;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        myReclerView.setLayoutManager(new LinearLayoutManager(activity));
        myReclerView.setAdapter(new GongzuojingliAdapter(activity,list));

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
