package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.CZProductAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CZListActivity extends BaseActivity {

    @BindView(R.id.recy_cz_list)
    RecyclerView recy_cz_list;
    @BindView(R.id.txt_city)
    TextView txt_city;
    @BindView(R.id.img_list_fabu)
    ImageView img_list_fabu;
    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_list;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recy_cz_list.setLayoutManager(new LinearLayoutManager(activity));
        recy_cz_list.setAdapter(new CZProductAdapter(activity,list));

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.lil_city,R.id.img_list_fabu})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.lil_city:
                showCityList(txt_city);
                break;
            case R.id.img_list_fabu:
                startActivity(new Intent(CZListActivity.this,CZPublishActivity.class));
                break;
        }
    }


    /**
     * 选择城市
     * @param textView
     */
    public void showCityList(TextView textView){
        CityPicker.from(CZListActivity.this)
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
