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
import com.ssd.yiqiwa.model.entity.MacRentOutPoBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
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
public class CZProductAdapter extends RecyclerView.Adapter<CZProductAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<MacRentOutPoBean> mList;


    public CZProductAdapter(Context context, List<MacRentOutPoBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cz_product, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MacRentOutPoBean macRentOutPoBean = mList.get(position);
        Glide.with(context).load(Constants.ALIYUN_IMAGE_SSO+macRentOutPoBean.getCoverImage()).into(holder.imageview);
        if(macRentOutPoBean.getBoutique().equals("1")) {
            holder.img_type.setImageResource(R.mipmap.ic_jingxuan);
        }
        holder.txt_cart_product_title.setText(macRentOutPoBean.getTitle());
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
        holder.txt_cart_product_type.setText(sb.toString());
        holder.txt_product_price.setText(macRentOutPoBean.getPriceDay());
        holder.txt_product_date.setText(DateFormatUtil.getDateFormat(macRentOutPoBean.getCreateDate(),DateFormatUtil.FORMAT_yyyyMMdd));
        holder.lil_item_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context,CZDetailActivity.class);
                intent.putExtra("productRoId",macRentOutPoBean.getRoId());
                startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lil_item_product)
        LinearLayout lil_item_product;
        @BindView(R.id.imageview)
        ImageView imageview;
        @BindView(R.id.img_type)
        ImageView img_type;

        @BindView(R.id.txt_cart_product_title)
        TextView txt_cart_product_title;
        @BindView(R.id.txt_cart_product_type)
        TextView txt_cart_product_type;
        @BindView(R.id.txt_product_price)
        TextView txt_product_price;
        @BindView(R.id.txt_product_date)
        TextView txt_product_date;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
