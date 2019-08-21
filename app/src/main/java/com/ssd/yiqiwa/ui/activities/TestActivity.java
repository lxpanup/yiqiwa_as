package com.ssd.yiqiwa.ui.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.HomeProductAdapter;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;


public class TestActivity extends BaseActivity {

    @BindView(R.id.test_banner)
    Banner testBanner;
    @BindView(R.id.test_img_picasso)
    ImageView testImgPicasso;
    @BindView(R.id.test_recy)
    RecyclerView test_recy;

    @Override
    public Object offerLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void onBindView() {
        String[] urls = getResources().getStringArray(R.array.url);
        List list = Arrays.asList(urls);
        List<?> images = new ArrayList(list);
        //简单使用
        testBanner.setImages(images)
                .setImageLoader(new GlideImageLoader())
                .start();




        List<String> listProduct = new ArrayList<>();
        listProduct.add("1");
        listProduct.add("1");
        listProduct.add("1");
        listProduct.add("1");
        listProduct.add("1");
        LinearLayoutManager layoutManager =  new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        test_recy.setLayoutManager(layoutManager);//这里用线性宫格显示 类似于gridview
        test_recy.setAdapter(new HomeProductAdapter(TestActivity.this,listProduct));

    }

    @Override
    public void destory() {

    }
}
