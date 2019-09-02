package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSDetailActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZDetailActivity;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.DateFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Create by SunnyDay on 2019/03/21
 * <p>
 * 商品列表adapter
 */
public class HomeProductAdapter extends RecyclerView.Adapter<HomeProductAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<ProductBean> mList;


    public HomeProductAdapter(Context context, List<ProductBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_product, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        ProductBean productBean = mList.get(position);
        holder.txt_product_name.setText(productBean.getTitle());
        StringBuilder sb = new StringBuilder();
        sb.append(productBean.getProvince()+productBean.getCity());
        if(!productBean.getFactoryDate().isEmpty()){
            sb.append("|");
            sb.append(productBean.getFactoryDate().substring(0,4));
        }

        if(!productBean.getWorkHour().isEmpty()&&!productBean.getWorkHour().equals("0")){
            sb.append("|");
            sb.append(productBean.getWorkHour()+"");
        }

        holder.txt_product_category.setText(sb.toString());
        holder.txt_product_price.setText(productBean.getPrice()+"");
        holder.txt_product_priceuint.setText("元/"+productBean.getPriceUint());
        if(productBean.getType()==1) {
            holder.img_product_type.setImageResource(R.mipmap.ic_chuzhu);
        }else{
            holder.img_product_type.setImageResource(R.mipmap.ic_chushou);
        }
        Glide.with(context).load(Constants.ALIYUN_IMAGE_SSO+productBean.getCoverImage()).into(holder.img_product_coverimage);
        holder.lil_item_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if(productBean.getType()==1) {
                    intent.setClass(context,CZDetailActivity.class);
                }else{
                    intent.setClass(context, CSDetailActivity.class);
                }
                intent.putExtra("productRoId",productBean.getId()+"");
                startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_product_name)
        TextView txt_product_name;
        @BindView(R.id.txt_product_category)
        TextView txt_product_category;
        @BindView(R.id.txt_product_price)
        TextView txt_product_price;
        @BindView(R.id.txt_product_priceuint)
        TextView txt_product_priceuint;
        @BindView(R.id.img_product_coverimage)
        ImageView img_product_coverimage;
        @BindView(R.id.img_product_type)
        ImageView img_product_type;

        @BindView(R.id.lil_item_product)
        LinearLayout lil_item_product;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
