package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.model.entity.MacBuyPoBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSDetailActivity;
import com.ssd.yiqiwa.ui.activities.other.MessageChatActivity;
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
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<CartProductBean> mList;
    private OnCartCheckbox onCartCheckbox;

    public CartListAdapter(Context context, List<CartProductBean> mList,OnCartCheckbox onCartCheckbox) {
        this.context = context;
        this.mList = mList;
        this.onCartCheckbox = onCartCheckbox;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CartProductBean macRentOutPoBean = mList.get(position);
        Glide.with(context).load(Constants.ALIYUN_IMAGE_SSO+macRentOutPoBean.getCoverImage()).into(holder.imageview);

        holder.txt_cart_product_title.setText(macRentOutPoBean.getProductTile());

        holder.txt_cart_product_type.setText(macRentOutPoBean.getProjectType());
        holder.txt_gerenqiye.setText(macRentOutPoBean.getRentFrom());
        holder.txt_product_price.setText(macRentOutPoBean.getProductPrice());
//        holder.lil_item_product.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, CSDetailActivity.class);
//                intent.putExtra("productRoId",macRentOutPoBean.getProductId());
//                startActivity(intent);
//            }
//        });
        holder.chx_cart.setChecked(macRentOutPoBean.isCartCheckbox());
        holder.chx_cart.setOnCheckedChangeListener((buttonView, isChecked) -> {
            onCartCheckbox.onCartChebox(position,isChecked);
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview)
        ImageView imageview;

        @BindView(R.id.chx_cart)
        CheckBox chx_cart;
        @BindView(R.id.txt_cart_product_title)
        TextView txt_cart_product_title;
        @BindView(R.id.txt_cart_product_type)
        TextView txt_cart_product_type;
        @BindView(R.id.txt_product_price)
        TextView txt_product_price;
        @BindView(R.id.txt_gerenqiye)
        TextView txt_gerenqiye;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public interface OnCartCheckbox{
        void onCartChebox(int postion,boolean isChecked);
    }

}
