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

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.adapter.IntegralAdapter;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;
import com.ssd.yiqiwa.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 预约订单列表
 */
public class YuyueListActivity extends BaseActivity {


    private Activity activity;
    private Context context;


    @BindView(R.id.recy_yuyue_list)
    RecyclerView recyYuyueList;

    private YuyueListAdapter yuyueListAdapter;

    private List<MacOrderSubPo> macOrderSubPos = new ArrayList<>();

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


        recyYuyueList.setLayoutManager(new LinearLayoutManager(activity));
        yuyueListAdapter = new YuyueListAdapter(activity, macOrderSubPos, this::getOderFollowOrder);
        recyYuyueList.setAdapter(yuyueListAdapter);

        getOrderMangerReserveOrder();
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



    /**
     * 获取产品信息
     */
    public void getOrderMangerReserveOrder(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<PagesBean<MacOrderSubPo>>> call = request.orderMangerReserveOrder(18,1);
        call.enqueue(new Callback<BaseBean<PagesBean<MacOrderSubPo>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<PagesBean<MacOrderSubPo>>> call, Response<BaseBean<PagesBean<MacOrderSubPo>>> response) {
                hideDialog();
                BaseBean<PagesBean<MacOrderSubPo>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    macOrderSubPos.addAll(baseBeanList.getData().getRecords());
                    yuyueListAdapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<PagesBean<MacOrderSubPo>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }


    /**
     * 跟进低订单
     */
    public void getOderFollowOrder(int postion,int osId){
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.orderFollowOrder(18,osId);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                JsonEntity baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    macOrderSubPos.remove(postion);
                    yuyueListAdapter.notifyDataSetChanged();
                    ToastUtils.showLong(baseBeanList.getMsg());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<JsonEntity> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }






}
