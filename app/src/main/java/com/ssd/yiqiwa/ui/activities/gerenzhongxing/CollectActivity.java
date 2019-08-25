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
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 我的收藏
 */
public class CollectActivity extends BaseActivity {


    private Activity activity;
    private Context context;



    @BindView(R.id.view_pager_collect)
    ViewPager mViewPager;
    @BindView(R.id.tab_collect)
    TabLayout myTabLayout;

    public static void start(Context context, int myfbpostion){
        Intent intent=new Intent(context, CollectActivity.class);
        intent.putExtra("myscpostion",myfbpostion);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_collect;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        FragmentTabAdapter mMyAdapter = new FragmentTabAdapter(getSupportFragmentManager(),new String[]{"出租","出售"});
        mMyAdapter.addFragment(new CZPublishFragment());
        mMyAdapter.addFragment(new CZPublishFragment());
        mViewPager.setAdapter(mMyAdapter);

        myTabLayout.setupWithViewPager(mViewPager);
        myTabLayout.setTabMode(TabLayout.MODE_FIXED);
        int myfbpostion = getIntent().getIntExtra("myscpostion",0);
        mViewPager.setCurrentItem(myfbpostion);
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
