package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;
import com.ssd.yiqiwa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 预约订单列表
 */
public class QYGengJinListActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recy_yuyue_list)
    RecyclerView recyYuyueList;
    @BindView(R.id.txt_title)
    TextView txt_title;

    private int pageNo = 1;

    private YuyueListAdapter yuyueListAdapter;

    private List<MacOrderSubPo> macOrderSubPos = new ArrayList<>();

    public static void start(Context context){
        Intent intent=new Intent(context, QYGengJinListActivity.class);
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


        txt_title.setText("跟进订单");
        recyYuyueList.setLayoutManager(new LinearLayoutManager(activity));
        yuyueListAdapter = new YuyueListAdapter(activity, macOrderSubPos, this::getOderFollowOrder);
        recyYuyueList.setAdapter(yuyueListAdapter);
        showDialog();
        getOrderMangerReserveOrder();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                macOrderSubPos.clear();
                pageNo = 0;
                getOrderMangerReserveOrder();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                getOrderMangerReserveOrder();
            }
        });
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
        LogUtils.e("数量:"+pageNo);
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<PagesBean<MacOrderSubPo>>> call = request.orderMangerFollowOrder(18,pageNo);
        call.enqueue(new Callback<BaseBean<PagesBean<MacOrderSubPo>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<PagesBean<MacOrderSubPo>>> call, Response<BaseBean<PagesBean<MacOrderSubPo>>> response) {
                hideDialog();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
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
                hideDialog();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
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
