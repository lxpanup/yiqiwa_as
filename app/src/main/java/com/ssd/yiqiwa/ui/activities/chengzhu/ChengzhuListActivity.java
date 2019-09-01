package com.ssd.yiqiwa.ui.activities.chengzhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.MacRentIntPoBean;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZPublishActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZShaixuanActivity;
import com.ssd.yiqiwa.ui.adapter.CZProductAdapter;
import com.ssd.yiqiwa.ui.adapter.ChengzhuProductAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.zaaach.citypicker.CityPicker;
import com.zaaach.citypicker.adapter.OnPickListener;
import com.zaaach.citypicker.model.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 雇主承租-机主出租
 */
public class ChengzhuListActivity extends BaseActivity {

    @BindView(R.id.recy_cz_list)
    RecyclerView recy_cz_list;
    @BindView(R.id.txt_city)
    TextView txt_city;
    @BindView(R.id.img_list_fabu)
    ImageView img_list_fabu;
    private Activity activity;
    private Context context;

    private List<MacRentIntPoBean> macRentOutPoBeans = new ArrayList<>();

    private ChengzhuProductAdapter czProductAdapter;

    @Override
    public Object offerLayout() {
        return R.layout.chengzhu_activity_list;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        recy_cz_list.setLayoutManager(new LinearLayoutManager(activity));
        czProductAdapter =  new ChengzhuProductAdapter(activity,macRentOutPoBeans);
        recy_cz_list.setAdapter(czProductAdapter);
        getRentInList();
    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.lil_city,R.id.img_list_fabu,R.id.txt_list_saixuan})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.lil_city:
                showCityList(txt_city);
                break;
            case R.id.img_list_fabu:
                startActivity(new Intent(ChengzhuListActivity.this,CZPublishActivity.class));
                break;
            case R.id.txt_list_saixuan:
                startActivity(new Intent(ChengzhuListActivity.this,CZShaixuanActivity.class));
                break;

        }
    }


    /**
     * 选择城市
     * @param textView
     */
    public void showCityList(TextView textView){
        CityPicker.from(ChengzhuListActivity.this)
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



    /**
     * 获取产品信息
     */
    public void getRentInList(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<PagesBean<MacRentIntPoBean>>> call = request.rentInList(1);
        call.enqueue(new Callback<BaseBean<PagesBean<MacRentIntPoBean>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<PagesBean<MacRentIntPoBean>>> call, Response<BaseBean<PagesBean<MacRentIntPoBean>>> response) {
                hideDialog();
                BaseBean<PagesBean<MacRentIntPoBean>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    macRentOutPoBeans.addAll( baseBeanList.getData().getRecords());
                    czProductAdapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<PagesBean<MacRentIntPoBean>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }

}
