package com.ssd.yiqiwa.ui.activities.jizhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.CollectActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.fragment.jizhu.JizhuzhaopingFragment;
import com.ssd.yiqiwa.utils.Constants;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 机主列表
 */
public class JizhuListActivity extends BaseActivity {



    private Activity activity;
    private Context context;

    @BindView(R.id.view_pager_jizhu)
    ViewPager mViewPager;
    @BindView(R.id.tab_jizhu)
    TabLayout myTabLayout;

    @BindView(R.id.txt_city)
    TextView txt_city;

    public static void start(Context context, int myfbpostion){
        Intent intent=new Intent(context, JizhuListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("mysfbostion",myfbpostion);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.jz_activity_jizhulist;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();


        FragmentTabAdapter mMyAdapter = new FragmentTabAdapter(getSupportFragmentManager(),new String[]{"机主招聘","操作手应聘"});
        mMyAdapter.addFragment(new JizhuzhaopingFragment());
        mMyAdapter.addFragment(new JizhuzhaopingFragment());
        mViewPager.setAdapter(mMyAdapter);

        myTabLayout.setupWithViewPager(mViewPager);
        myTabLayout.setTabMode(TabLayout.MODE_FIXED);
        int myfbpostion = getIntent().getIntExtra("mysfbostion",0);
        mViewPager.setCurrentItem(myfbpostion);

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.lil_city})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.lil_city:
                showCityList(txt_city);
                break;

        }
    }


    /**
     * 选择城市
     * @param textView
     */
    public void showCityList(TextView textView){
        CityPicker.from(JizhuListActivity.this)
                .setHotCities(Constants.getHotCitys())
                .setOnPickListener(new OnPickListener() {
                    @Override
                    public void onPick(int position, City data) {
                        textView.setText(data.getName());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "取消选择", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLocate() {
                        //开始定位，这里模拟一下定位
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                CityPicker.from(MainActivity.this).locateComplete(new LocatedCity("深圳", "广东", "101280601"), LocateState.SUCCESS);
//                            }
//                        }, 3000);
                    }
                })
                .show();
    }


}
