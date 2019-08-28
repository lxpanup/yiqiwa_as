package com.ssd.yiqiwa.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.common.LoginActivity;
import com.ssd.yiqiwa.ui.adapter.HomeAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Smile Wei
 * @since 2017/03/01.
 */

public class NavHomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,
        LoadMoreRecyclerView.OnLoadMoreListener {


    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    LoadMoreRecyclerView recyclerView;

    HomeAdapter adapter;


    private List<HomeBase> list = new ArrayList<>();

    public static NavHomeFragment newInstance() {
        NavHomeFragment fragment = new NavHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int offerLayout() {
        return R.layout.z_fragment_navigation_home;
    }

    @Override
    public void onBindView() {

        refreshLayout.setColorSchemeResources(R.color.font_orange_color);
        refreshLayout.setOnRefreshListener(this);
        int spanCount = getResources().getInteger(R.integer.grid_span_count);

        GridLayoutManager layoutManager = new GridLayoutManager(activity, spanCount);
//        getTestList();
        adapter = new HomeAdapter(context, activity,list);
        layoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadMoreListener(this);


        getHomeBanner();
    }

    @Override
    public void destory() {

    }



    @Override
    public void onRefresh() {
        Log.e("HomeFragment","------onRefresh-----");
        setRefreshLoading(false);
    }

    @Override
    public void onLoadMore() {
        Log.e("HomeFragment","------onLoadMore-----");
        setRefreshLoading(true);
    }



    /**
     * 设置刷新和加载更多的状态
     *
     * @param isLoading 加载更多状态
     */
    private void setRefreshLoading(boolean isLoading) {
        if (!isLoading) {
            recyclerView.setLoading(false);
            refreshLayout.setRefreshing(false);
        }
    }




    public void getHomeBanner(){

        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<HomeBannerImages>> call = request.homeBanner();
        call.enqueue(new Callback<BaseBeanList<HomeBannerImages>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<HomeBannerImages>> call, Response<BaseBeanList<HomeBannerImages>> response) {
                hideDialog();
                BaseBeanList<HomeBannerImages> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    List<HomeBannerImages> bannerImages = baseBeanList.getData();
                    List<String> imgList = new ArrayList<>();
                    for(HomeBannerImages item:bannerImages){
                        imgList.add(item.getImageUrl());
                    }
                    adapter.setHomeBannerImages(imgList);
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
                getHomeDiscountZone();
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<HomeBannerImages>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }





    public void getHomeDiscountZone(){

        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<ProductBean>> call = request.homeDiscountZone();
        call.enqueue(new Callback<BaseBeanList<ProductBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<ProductBean>> call, Response<BaseBeanList<ProductBean>> response) {
                hideDialog();
                BaseBeanList<ProductBean> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    adapter.homeProduct(baseBeanList.getData());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
                getHomeNewProduct();

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<ProductBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }


    public void getHomeNewProduct(){

        Api request = getRetrofit().create(Api.class);
        Call<BaseBeanList<ProductBean>> call = request.homeNewProduct();
        call.enqueue(new Callback<BaseBeanList<ProductBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBeanList<ProductBean>> call, Response<BaseBeanList<ProductBean>> response) {
                hideDialog();
                BaseBeanList<ProductBean> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    getTestList();
                    for (ProductBean item:baseBeanList.getData()) {
                        list.add(new HomeBase(item.getCity(), item.getCoverImage(), item.getCreateDate(), item.getFactoryDate(), item.getId()+"", item.getPrice()+"",
                                item.getPriceUint(), item.getProvince(), item.getTitle(), item.getType()+"", item.getWorkHour()+"", HomeBase.TYPE_RECOMMEND, 150));
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<ProductBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }


    private void getTestList(){
        //模拟返回数据
        int spanCount = 300;
        list.clear();
        //list添加轮播图片
        list.add(new HomeBase("", "","", "","", "",
                "", "","", "","", HomeBase.TYPE_CAROUSEL, spanCount));
        //list添加分类
        list.add(new HomeBase("", "","", "","", "",
                "", "","", "","", HomeBase.TYPE_CATEGORY, spanCount));
        //list
        list.add(new HomeBase("", "","", "","", "",
                "", "","", "","",  HomeBase.TYPE_HEADLINE, spanCount));

        list.add(new HomeBase("", "","", "","", "",
                "", "","", "","", HomeBase.TYPE_DIVIDER, spanCount));
        list.add(new HomeBase("", "","", "","", "",
                "", "","推荐商品", "","", HomeBase.TYPE_LIVE, spanCount));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
//        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        //list添加头部
//        list.add(new HomeBase(0, 0, "", "热门直播", HomeBase.TYPE_HEADER, spanCount));



    }
}
