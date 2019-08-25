package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.PingzujiluListAdapter;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 凭租记录
 */
public class PingzujiluListActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    @BindView(R.id.recy_pingzujilu_list)
    RecyclerView myRecycler;


    public static void start(Context context){
        Intent intent=new Intent(context, PingzujiluListActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_pingzujilu_list;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        myRecycler.setLayoutManager(new LinearLayoutManager(activity));
        myRecycler.setAdapter(new PingzujiluListAdapter(activity,list));
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
