package com.ssd.yiqiwa.ui.activities.publish;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 承租发布
 */
public class MyPublishActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    @BindView(R.id.view_pager_fabu)
    ViewPager mViewPager;
    @BindView(R.id.tab_fabu)
    TabLayout tabFabu;

    public static void start(Context context, int myfbpostion){
        Intent intent=new Intent(context, MyPublishActivity.class);
        intent.putExtra("myfbpostion",myfbpostion);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.fb_activity_my;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        FragmentTabAdapter mMyAdapter = new FragmentTabAdapter(getSupportFragmentManager(),new String[]{"出租","出售","承租","购买"});
        mMyAdapter.addFragment(new CZPublishFragment());
        mMyAdapter.addFragment(new CZPublishFragment());
        mMyAdapter.addFragment(new CZPublishFragment());
        mMyAdapter.addFragment(new CZPublishFragment());
        mViewPager.setAdapter(mMyAdapter);

        tabFabu.setupWithViewPager(mViewPager);
        tabFabu.setTabMode(TabLayout.MODE_FIXED);
        int myfbpostion = getIntent().getIntExtra("myfbpostion",0);
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
