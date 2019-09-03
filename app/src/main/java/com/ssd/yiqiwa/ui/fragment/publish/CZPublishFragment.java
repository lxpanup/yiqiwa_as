package com.ssd.yiqiwa.ui.fragment.publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.MacRentOutPoBean;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.ui.adapter.CZProductAdapter;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.PublicshProductAdapter;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;
import com.ssd.yiqiwa.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class CZPublishFragment extends BaseFragment {


    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recy_cz_list)
    RecyclerView recy_cz_list;


    private List<MacRentOutPoBean> macRentOutPoBeans = new ArrayList<>();

    private CZProductAdapter czProductAdapter;

    @Override
    protected int offerLayout() {
        return R.layout.fb_fragment_cz;
    }

    @Override
    public void onBindView() {

        czProductAdapter = new CZProductAdapter(getActivity(),macRentOutPoBeans);
        recy_cz_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_cz_list.setAdapter(czProductAdapter);

        showDialog();
        getUserRentOutInfo();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                macRentOutPoBeans.clear();
                getUserRentOutInfo();
            }
        });

    }

    @Override
    public void destory() {

    }



    /**
     *
     */
    public void getUserRentOutInfo(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<MacRentOutPoBean>> call = request.userRentOutInfo(1);
        call.enqueue(new Callback<BaseBeanList<MacRentOutPoBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<MacRentOutPoBean>> call, Response<BaseBeanList<MacRentOutPoBean>> response) {
                hideDialog();
                refreshLayout.finishLoadMore();
                BaseBeanList<MacRentOutPoBean> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    macRentOutPoBeans.addAll(baseBeanList.getData());
                    czProductAdapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<MacRentOutPoBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                hideDialog();
                refreshLayout.finishLoadMore();
            }
        });
    }




}

