package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.adapter.IntegralAdapter;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 预约订单列表
 */
public class YuyueListActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    @BindView(R.id.recy_yuyue_list)
    RecyclerView recyYuyueList;


    public static void start(Context context){
        Intent intent=new Intent(context, YuyueListActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_yuyuelist;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recyYuyueList.setLayoutManager(new LinearLayoutManager(activity));
        recyYuyueList.setAdapter(new YuyueListAdapter(activity,list));
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
