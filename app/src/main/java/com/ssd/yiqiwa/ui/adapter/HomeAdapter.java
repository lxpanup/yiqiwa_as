package com.ssd.yiqiwa.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.base.Type;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.model.entity.HomeTop;
import com.ssd.yiqiwa.widget.CirclePageIndicator;
import com.ssd.yiqiwa.widget.FooterLoading;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Smile Wei
 * @since 2017/03/01.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Activity activity;
    private List<?> homeBannerImages = new ArrayList<>();
    private List<HomeBase> list;
    private final SpanSizeLookup spanSizeLookup = new SpanSizeLookup();
    private LayoutInflater inflater;

    public HomeAdapter(Context context, Activity activity,List<HomeBase> list) {
        this.context = context;
        this.activity = activity;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return spanSizeLookup;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case HomeBase.TYPE_CAROUSEL:
                view = inflater.inflate(R.layout.item_home_type_carousel, parent, false);
                return new CarouselHolder(view);
            case HomeBase.TYPE_CATEGORY:
                view = inflater.inflate(R.layout.fragment_navigation_home_category, parent, false);
                return new CategoryHolder(view);
            case HomeBase.TYPE_HEADLINE:
                view = inflater.inflate(R.layout.item_home_type_boutique, parent, false);
                return new BoutiqueHolder(view);
            case HomeBase.TYPE_DIVIDER:
                view = inflater.inflate(R.layout.item_home_type_special, parent, false);
                return new SpecialHolder(view);
            case HomeBase.TYPE_RECOMMEND:
                view = inflater.inflate(R.layout.item_home_type_recommend, parent, false);
                return new RecommendHolder(view);
//            case HomeBase.TYPE_PLACE:
//                view = inflater.inflate(R.layout.item_place, parent, false);
//                return new PlaceHolder(view);
            default:
                view = inflater.inflate(R.layout.item_footer_loading, parent, false);
                return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof CarouselHolder) {
            CarouselHolder holder = (CarouselHolder) viewHolder;
        }  else if (viewHolder instanceof FooterHolder) {
            FooterHolder holder = (FooterHolder) viewHolder;
            holder.footerLoading.onLoad(Type.TYPE_FOOTER_FULL == list.get(position).getType());
        } else if(viewHolder instanceof SpecialHolder){
            SpecialHolder holder = (SpecialHolder) viewHolder;
            List<String> listProduct = new ArrayList<>();
            listProduct.add("1");
            listProduct.add("1");
            listProduct.add("1");
            listProduct.add("1");
            listProduct.add("1");
            LinearLayoutManager layoutManager =  new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rcy_home_special.setLayoutManager(layoutManager);//这里用线性宫格显示 类似于gridview
            holder.rcy_home_special.setAdapter(new HomeProductAdapter(context,listProduct));
        } else if(viewHolder instanceof RecommendHolder){

        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    private class SpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
        @Override
        public int getSpanSize(int position) {
            return list.get(position).getSpanCount();
        }
    }



    class CarouselHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banner)
        Banner banner;


        CarouselHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            String[] urls = activity.getResources().getStringArray(R.array.url);
            List list = Arrays.asList(urls);
            homeBannerImages = new ArrayList(list);
            //简单使用
            banner.setImages(homeBannerImages)
                    .setImageLoader(new GlideImageLoader())
                    .start();

        }
    }


    class BoutiqueHolder extends RecyclerView.ViewHolder {

        BoutiqueHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick({R.id.img_home_woyaochengzhu,R.id.img_home_woyaogoumai})
        public void onViewClick(View v){
            switch (v.getId()){
                case R.id.img_home_woyaochengzhu:
                    ToastUtils.showLong("我的承租");
                    break;
                case R.id.img_home_woyaogoumai:
                    ToastUtils.showLong("我的购买");
                    break;
            }
        }
    }

    class SpecialHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rcy_home_special)
        RecyclerView rcy_home_special;

        SpecialHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    class RecommendHolder extends RecyclerView.ViewHolder {

        RecommendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    private class PlaceHolder extends RecyclerView.ViewHolder {
        PlaceHolder(View itemView) {
            super(itemView);
        }
    }


    class CategoryHolder extends RecyclerView.ViewHolder {

        CategoryHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class FooterHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.footer)
        FooterLoading footerLoading;

        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
