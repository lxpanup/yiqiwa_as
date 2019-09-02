package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.model.entity.MacRentOutPoBean;
import com.ssd.yiqiwa.model.entity.PictureBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.DateFormatUtil;
import com.ssd.yiqiwa.utils.SharedPreferencesUtils;
import com.ssd.yiqiwa.widget.CirclePageIndicator;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GradientScrollView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CZDetailActivity extends BaseActivity {

    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.detail_banner)
    Banner detailBanner;

    @BindView(R.id.txt_product_name)
    TextView txt_product_name;
    @BindView(R.id.txt_product_time)
    TextView txt_product_time;
    @BindView(R.id.txt_product_num)
    TextView txt_product_num;
    @BindView(R.id.txt_city_head)
    TextView txt_city_head;
    @BindView(R.id.txt_head_01)
    TextView txt_head_01;
    @BindView(R.id.txt_head_02)
    TextView txt_head_02;
    @BindView(R.id.txt_head_03)
    TextView txt_head_03;
    @BindView(R.id.txt_head_04)
    TextView txt_head_04;
    @BindView(R.id.txt_product_price_01)
    TextView txt_product_price_01;
    @BindView(R.id.txt_product_price_02)
    TextView txt_product_price_02;
    @BindView(R.id.txt_product_price_03)
    TextView txt_product_price_03;

    @BindView(R.id.txt_cs_01)
    TextView txt_cs_01;
    @BindView(R.id.txt_cs_02)
    TextView txt_cs_02;
    @BindView(R.id.txt_cs_03)
    TextView txt_cs_03;
    @BindView(R.id.txt_cs_04)
    TextView txt_cs_04;
    @BindView(R.id.txt_xqsm)
    TextView txt_xqsm;
    @BindView(R.id.img_sfzy)
    ImageView img_sfzy;

    private Activity activity;
    private Context context;

    private List<String> homeBannerImages;

    private String productRoId;

    private String productId;
    private String productType;
    private String rentFrom;
    private String coverImage;
    private String productTile;
    private String projectType;
    private String productPrice;
    private String priceDay;
    private String priceHour;
    private String priceMonth;
    private String productPriceUint;
    private boolean isCartCheckbox;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_detail;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        productRoId = getIntent().getStringExtra("productRoId");



        detailBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                photoShowDialog(position);
            }
        });

        getRentOutDetail(Integer.parseInt(productRoId));
    }

    @Override
    public void destory() {

    }



    @OnClick({R.id.img_back,R.id.tv_cart,R.id.tv_buyer})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_cart:


                ToastUtils.showLong(Constants.addCartListMessage(new CartProductBean(productId,productType,
                        rentFrom,coverImage,productTile,projectType,"",priceDay,priceHour,priceMonth,productPriceUint,"","",false)));


                break;
            case R.id.tv_buyer:
                startActivity(new Intent(activity,CartConfirmActivity.class));
                break;
        }
    }



    /**
     * 查看图片
     */
    private void photoShowDialog(int position){
        Dialog dia = new Dialog(activity, R.style.imageDialog);
        dia.setContentView(R.layout.item_image_banner);
        Banner banner =  dia.findViewById(R.id.banner);
//        uriList.remove(uriList.size()-1);
        banner.setImageLoader(new GlideImageLoader())
                .setImages(homeBannerImages)
                .isAutoPlay(false)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .start();
        banner.setIndicatorGravity(position);

        ImageView imageView = dia.findViewById(R.id.img_count_down);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

        Window window = dia.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0;
        window.setAttributes(layoutParams);
        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }



    /**
     * 获取产品信息
     */
    public void getRentOutDetail(int roId){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<MacRentOutPoBean>> call = request.rentOutDetail(roId);
        call.enqueue(new Callback<BaseBean<MacRentOutPoBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<MacRentOutPoBean>> call, Response<BaseBean<MacRentOutPoBean>> response) {
                hideDialog();
                BaseBean<MacRentOutPoBean> baseBean = response.body();
                if(baseBean.getCode()== Constants.HTTP_RESPONSE_OK){
                    initViewData(baseBean.getData());
                }else{
                    ToastUtils.showLong(baseBean.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<MacRentOutPoBean>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }


    private void initViewData(MacRentOutPoBean macRentOutPoBean){

        homeBannerImages = new ArrayList<>();
        for(PictureBean item : macRentOutPoBean.getPictureList()){
            homeBannerImages.add(item.getUrl());
        }
        //简单使用
        detailBanner.setImages(homeBannerImages)
                .setImageLoader(new GlideImageLoader())
                .start();
        txt_product_name.setText(macRentOutPoBean.getTitle());
        txt_product_time.setText(DateFormatUtil.getDateFormatYMD(macRentOutPoBean.getCreateDate()));


        txt_product_num.setText(macRentOutPoBean.getViewAmount());
        txt_city_head.setText(macRentOutPoBean.getCity());

        txt_head_01.setText(DateFormatUtil.getDateFormat(macRentOutPoBean.getFactoryDate(),DateFormatUtil.FORMAT_yyyy));
        if(macRentOutPoBean.getBoutique().equals("1")) {
            txt_head_02.setText("精品");
        }else{
            txt_head_02.setVisibility(View.GONE);
        }
        txt_head_03.setText(macRentOutPoBean.getRentFrom());
        txt_head_04.setText(macRentOutPoBean.getWorkTime()+macRentOutPoBean.getWorkTimeUint());

        txt_product_price_01.setText(macRentOutPoBean.getPriceHour());
        txt_product_price_02.setText(macRentOutPoBean.getPriceDay());
        txt_product_price_03.setText(macRentOutPoBean.getPriceMonth());
//        txt_cs_01.setText(macRentOutPoBean.getMbName()+"/"+macRentOutPoBean.getMbmName());
        txt_cs_01.setText(macRentOutPoBean.getMbName());
        txt_cs_02.setText(macRentOutPoBean.getMtName());
        txt_cs_03.setText(macRentOutPoBean.getStandard());
        txt_cs_04.setText(macRentOutPoBean.getCapacity());
        txt_xqsm.setText(macRentOutPoBean.getDescribes());
        if(macRentOutPoBean.getSelfOperateGoods().equals("1")&&macRentOutPoBean.getSelfProtectGoods().equals("1")) {
            img_sfzy.setImageResource(R.mipmap.ic_detail_head_3);
        }else if(macRentOutPoBean.getSelfOperateGoods().equals("1")) {
            img_sfzy.setImageResource(R.mipmap.ic_detail_head_1);
        }else if(macRentOutPoBean.getSelfProtectGoods().equals("1")){
            img_sfzy.setImageResource(R.mipmap.ic_detail_head_2);
        }


        productId = macRentOutPoBean.getRoId();
        productType = "2";
        rentFrom = macRentOutPoBean.getRentFrom();
        coverImage = macRentOutPoBean.getCoverImage();
        productTile = macRentOutPoBean.getTitle();
        StringBuilder sb = new StringBuilder();
        sb.append(macRentOutPoBean.getProvince()+macRentOutPoBean.getCity());
        if(!macRentOutPoBean.getFactoryDate().isEmpty()){
            sb.append("|");
            sb.append(macRentOutPoBean.getFactoryDate().substring(0,4));
        }
        if(!macRentOutPoBean.getWorkTime().isEmpty()){
            sb.append("|");
            sb.append(macRentOutPoBean.getWorkTime()+macRentOutPoBean.getWorkTimeUint());
        }
        projectType = sb.toString();

        priceDay = macRentOutPoBean.getPriceDay();
        priceHour = macRentOutPoBean.getPriceHour();
        priceMonth = macRentOutPoBean.getPriceMonth();


        productPriceUint = "";
    }


}
