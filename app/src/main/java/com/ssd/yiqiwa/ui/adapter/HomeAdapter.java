package com.ssd.yiqiwa.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.base.Type;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZListActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZPublishActivity;
import com.ssd.yiqiwa.ui.activities.jizhu.JizhuListActivity;
import com.ssd.yiqiwa.ui.activities.other.SearchActivity;
import com.ssd.yiqiwa.ui.activities.other.XingxifeiActivity;
import com.ssd.yiqiwa.ui.activities.publish.ChengZuPublishActivity;
import com.ssd.yiqiwa.widget.FooterLoading;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            case HomeBase.TYPE_LIVE:
                view = inflater.inflate(R.layout.item_home_type_header, parent, false);
                return new HeaderHolder(view);
            default:
                view = inflater.inflate(R.layout.item_footer_loading, parent, false);
                return new FooterHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HomeBase bean = list.get(position);
        if (viewHolder instanceof CarouselHolder) {
            CarouselHolder holder = (CarouselHolder) viewHolder;
        }  else if (viewHolder instanceof FooterHolder) {
            FooterHolder holder = (FooterHolder) viewHolder;
            holder.footerLoading.onLoad(Type.TYPE_FOOTER_FULL == list.get(position).getType());
        } else if (viewHolder instanceof HeaderHolder) {
            HeaderHolder holder = (HeaderHolder) viewHolder;
            holder.tvTitle.setText(bean.getName());
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
            holder.rcy_home_special.setFocusable(false);
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
        @BindView(R.id.txt_city)
        TextView txt_city;


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

        @OnClick({R.id.lil_city_list,R.id.txt_search})
        public void onViewClick(View v){
            switch (v.getId()){
                case R.id.lil_city_list:
//                    activity.startActivity(new Intent(context, CityListActivity.class));
                    ((MainActivity)activity).showCityList(txt_city);
                    break;
                case R.id.txt_search:
                    activity.startActivity(new Intent(context, SearchActivity.class));
                    break;
            }
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
                    activity.startActivity(new Intent(context, CZListActivity.class));
                    break;
                case R.id.img_home_woyaogoumai:
                    activity.startActivity(new Intent(context, CZListActivity.class));
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

    class HeaderHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        HeaderHolder(View itemView) {
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

        @OnClick({R.id.tv_category_01,R.id.tv_category_02,R.id.tv_category_03,R.id.tv_category_04,
                R.id.tv_category_05,R.id.tv_category_06,R.id.tv_category_07,R.id.tv_category_08})
        public void onViewClick(View v) {
            switch (v.getId()) {
                case R.id.tv_category_01:
                    activity.startActivity(new Intent(context, XingxifeiActivity.class));
                    break;
                case R.id.tv_category_02:  //雇主承租
                    activity.startActivity(new Intent(context, ChengZuPublishActivity.class));
                    break;
                case R.id.tv_category_03:  //机主出售
                    activity.startActivity(new Intent(context, CZListActivity.class));
                    break;
                case R.id.tv_category_04: //二手购买
                    activity.startActivity(new Intent(context, CZListActivity.class));
                    break;
                case R.id.tv_category_05: //二手出售
                    activity.startActivity(new Intent(context, CZPublishActivity.class));
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
            }
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
