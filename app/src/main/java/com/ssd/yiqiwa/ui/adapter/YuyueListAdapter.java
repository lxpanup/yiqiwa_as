package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.MacRentIntPoBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.chengzhu.ChengzhuDetailActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.YuyueDetailedActivity;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.DateFormatUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Create by SunnyDay on 2019/03/21
 * <p>
 *  预约列表商品
 */
public class YuyueListAdapter extends RecyclerView.Adapter<YuyueListAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<MacOrderSubPo> mList;
    private OnClickGengjing onClickGengjing;

    public YuyueListAdapter(Context context, List<MacOrderSubPo> mList,OnClickGengjing onClickGengjing) {
        this.context = context;
        this.mList = mList;
        this.onClickGengjing = onClickGengjing;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_yuyue_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MacOrderSubPo macRentOutPoBean = mList.get(position);
        Glide.with(context).load(Constants.ALIYUN_IMAGE_SSO+macRentOutPoBean.getProductCoverImage()).into(holder.imageview);

        holder.txt_cart_product_title.setText(macRentOutPoBean.getProductTitle());
        StringBuilder sb = new StringBuilder();
        sb.append(macRentOutPoBean.getProvince()+macRentOutPoBean.getCity());


        holder.txt_cart_product_type.setText(macRentOutPoBean.getCity()+"  "+macRentOutPoBean.getRemark());

        holder.txt_product_stutas.setText(Constants.getOrderType(Integer.parseInt(macRentOutPoBean.getOrderType())));

        holder.txt_zongjinge.setText(macRentOutPoBean.getPrice());

        if(macRentOutPoBean.getStatus().equals("3")){
            holder.txt_cart_status.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.txt_cart_status.setText("订单成功");
        }else if(macRentOutPoBean.getStatus().equals("4")){
            holder.txt_cart_status.setText("订单失败");
            holder.txt_cart_status.setTextColor(context.getResources().getColor(R.color.red));
        }else if(macRentOutPoBean.getStatus().equals("2")){
        }else{
            holder.txt_cart_status.setVisibility(View.GONE);
            holder.txt_gengjing.setVisibility(View.VISIBLE);

        }



        holder.txt_gengjing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGengjing.onClickGengjing(position,Integer.parseInt(macRentOutPoBean.getOsId()));
            }
        });
        holder.lil_item_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, YuyueDetailedActivity.class);
                intent.putExtra("productId",macRentOutPoBean.getOsId());
                intent.putExtra("ordertype",macRentOutPoBean.getOrderType());
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

//        @BindView(R.id.img_type)
//        ImageView img_type;


        @BindView(R.id.txt_cart_product_title)
        TextView txt_cart_product_title;

        @BindView(R.id.txt_zongjinge)
        TextView txt_zongjinge;
        @BindView(R.id.txt_product_stutas)
        TextView txt_product_stutas;
        @BindView(R.id.txt_cart_product_type)
        TextView txt_cart_product_type;

        @BindView(R.id.txt_gengjing)
        TextView txt_gengjing;

        @BindView(R.id.txt_cart_status)
        TextView txt_cart_status;


        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }


    }



    public interface OnClickGengjing{
        void onClickGengjing(int postion,int osId);

    }

}
