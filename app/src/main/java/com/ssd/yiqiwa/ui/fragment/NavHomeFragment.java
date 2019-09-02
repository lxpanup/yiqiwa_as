package com.ssd.yiqiwa.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.HomeBaseBean;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.chengzhu.ChengzhuListActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSListActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZListActivity;
import com.ssd.yiqiwa.ui.activities.common.LoginActivity;
import com.ssd.yiqiwa.ui.activities.goumai.GoumaiListActivity;
import com.ssd.yiqiwa.ui.activities.jizhu.JizhuListActivity;
import com.ssd.yiqiwa.ui.activities.other.SearchActivity;
import com.ssd.yiqiwa.ui.activities.other.XingxifeiActivity;
import com.ssd.yiqiwa.ui.adapter.HomeAdapter;
import com.ssd.yiqiwa.ui.adapter.HomeMultipleAdapter;
import com.ssd.yiqiwa.ui.adapter.HomeProductAdapter;
import com.ssd.yiqiwa.ui.adapter.HomeQuickAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GlideImageThisLoader;
import com.ssd.yiqiwa.widget.LoadMoreRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Smile Wei
 * @since 2017/03/01.
 */

public class NavHomeFragment extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    RecyclerView rcyHomeSpecial;

    private Banner banner;
    private TextView txt_city;
    private List<String> bannerList = new ArrayList<>();
    private List<HomeBannerImages> homeBannerImages = new ArrayList<>();

//    HomeMultipleAdapter adapter;
    HomeQuickAdapter homeQuickAdapter;

    HomeProductAdapter homeProductAdapter;
    private List<ProductBean> homeProducts = new ArrayList<>();
    private List<ProductBean> productBeans = new ArrayList<>();

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
        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);

        homeQuickAdapter = new HomeQuickAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeQuickAdapter);

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.getLayout().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtils.showLong("下拉刷新");
                                refreshLayout.finishRefresh();
                            }
                        },2000);
                    }
                },2000);
            }
        });

        refreshLayout.setFooterHeight(100);


        //添加Header
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_type_carousel, recyclerView, false);

        header.findViewById(R.id.tv_category_01).setOnClickListener(this);
        header.findViewById(R.id.tv_category_02).setOnClickListener(this);
        header.findViewById(R.id.tv_category_03).setOnClickListener(this);
        header.findViewById(R.id.tv_category_04).setOnClickListener(this);
        header.findViewById(R.id.tv_category_05).setOnClickListener(this);
        header.findViewById(R.id.tv_category_06).setOnClickListener(this);
        header.findViewById(R.id.tv_category_07).setOnClickListener(this);
        header.findViewById(R.id.tv_category_08).setOnClickListener(this);
        header.findViewById(R.id.img_home_woyaochengzhu).setOnClickListener(this);
        header.findViewById(R.id.img_home_woyaogoumai).setOnClickListener(this);
        header.findViewById(R.id.lil_city_list).setOnClickListener(this);
        header.findViewById(R.id.txt_search).setOnClickListener(this);


        rcyHomeSpecial = header.findViewById(R.id.rcy_home_special);
        txt_city = header.findViewById(R.id.txt_city);


        LinearLayoutManager layoutManagerh =  new LinearLayoutManager(context);
        layoutManagerh.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcyHomeSpecial.setLayoutManager(layoutManagerh);//这里用线性宫格显示 类似于gridview
        homeProductAdapter = new HomeProductAdapter(context,homeProducts);
        rcyHomeSpecial.setAdapter(homeProductAdapter);
        rcyHomeSpecial.setFocusable(false);

        banner = header.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {

            }
        });
        homeQuickAdapter.addHeaderView(header);
        homeQuickAdapter.openLoadAnimation();
        getHomeBanner();
    }

    @Override
    public void destory() {

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
                    homeBannerImages = baseBeanList.getData();
                    List<String> imgList = new ArrayList<>();
                    for(HomeBannerImages item:homeBannerImages){
                        imgList.add(item.getImageUrl());
                    }
                    banner.setImages(imgList);
                    banner.start();
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
                getHomeDiscountZone();
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
//                    adapter.homeProduct(baseBeanList.getData());
                    homeProducts.clear();
                    homeProducts.addAll(baseBeanList.getData());
                    homeProductAdapter.notifyDataSetChanged();
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
                getHomeNewProduct();
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
                    productBeans.addAll(baseBeanList.getData());
//                    for (ProductBean item:baseBeanList.getData()) {
//                        list.add(new HomeBase(item.getCity(), item.getCoverImage(), item.getCreateDate(), item.getFactoryDate(), item.getId()+"", item.getPrice()+"",
//                                item.getPriceUint(), item.getProvince(), item.getTitle(), item.getType()+"", item.getWorkHour()+"", HomeBase.TYPE_RECOMMEND, 150));
//                    }
                    homeQuickAdapter.replaceData(productBeans);
//                    adapter.notifyDataSetChanged();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBeanList<ProductBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
//                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lil_city_list:
//                    activity.startActivity(new Intent(context, CityListActivity.class));
                ((MainActivity)activity).showCityList(txt_city);
                break;
            case R.id.txt_search:
                activity.startActivity(new Intent(context, SearchActivity.class));
                break;
            case R.id.tv_category_01:
                activity.startActivity(new Intent(context, XingxifeiActivity.class));
                break;
            case R.id.tv_category_02:  //雇主承租
                activity.startActivity(new Intent(context, CZListActivity.class));
                break;
            case R.id.tv_category_03:  //机主出售
                activity.startActivity(new Intent(context, ChengzhuListActivity.class));
                break;
            case R.id.tv_category_04: //二手购买
                activity.startActivity(new Intent(context, GoumaiListActivity.class));
                break;
            case R.id.tv_category_05: //二手出售
                activity.startActivity(new Intent(context, CSListActivity.class));
                break;
            case R.id.tv_category_06: //操作手服务
                JizhuListActivity.start(context,0);
                break;
            case R.id.tv_category_07: //维修配件
                activity.startActivity(new Intent(context, CZListActivity.class));
                break;
            case R.id.tv_category_08: //场地服务
                activity.startActivity(new Intent(context, CZListActivity.class));
                break;
            case R.id.img_home_woyaochengzhu:
                activity.startActivity(new Intent(context, CZListActivity.class));
                break;
            case R.id.img_home_woyaogoumai:
                activity.startActivity(new Intent(context, CZListActivity.class));
                break;
        }
    }

//    class CarouselHolder extends View {
//        @BindView(R.id.banner)
//        Banner banner;
//        @BindView(R.id.txt_city)
//        TextView txt_city;
//
//
//        CarouselHolder(View itemView) {
//            super();
//            ButterKnife.bind(this, itemView);
//
//            Log.e("yqw","homeBannerImages:"+homeBannerImages.size());
//            //简单使用
//            banner.setImages(homeBannerImages)
//                    .setImageLoader(new GlideImageLoader())
//                    .start();
//
//        }
//
//        @OnClick({R.id.lil_city_list,R.id.txt_search})
//        public void onViewClick(View v){
//            switch (v.getId()){
//                case R.id.lil_city_list:
////                    activity.startActivity(new Intent(context, CityListActivity.class));
//                    ((MainActivity)activity).showCityList(txt_city);
//                    break;
//                case R.id.txt_search:
//                    activity.startActivity(new Intent(context, SearchActivity.class));
//                    break;
//            }
//        }
//    }
}
