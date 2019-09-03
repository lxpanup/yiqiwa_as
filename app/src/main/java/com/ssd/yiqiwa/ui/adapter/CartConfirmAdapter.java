package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.utils.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Create by SunnyDay on 2019/03/21
 * <p>
 * 商品列表adapter
 */
public class CartConfirmAdapter extends RecyclerView.Adapter<CartConfirmAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<CartProductBean> mList;
    private OnAddOrderMessage onAddOrderMessage;

    public CartConfirmAdapter(Context context, List<CartProductBean> mList,OnAddOrderMessage onAddOrderMessage) {
        this.context = context;
        this.mList = mList;
        this.onAddOrderMessage = onAddOrderMessage;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_confirm_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CartProductBean macRentOutPoBean = mList.get(position);
        Glide.with(context).load(Constants.ALIYUN_IMAGE_SSO+macRentOutPoBean.getCoverImage()).into(holder.imageview);

        holder.txt_cart_product_title.setText(macRentOutPoBean.getProductTile());

        holder.txt_cart_product_type.setText(macRentOutPoBean.getProjectType());
        holder.txt_gerenqiye.setText(macRentOutPoBean.getRentFrom());
        if (macRentOutPoBean.getProductPrice().isEmpty()) {
            holder.lil_amount_postion.setVisibility(View.GONE);
            if(macRentOutPoBean.getPriceHour().isEmpty()&&macRentOutPoBean.getPriceDay().isEmpty()
                    &&macRentOutPoBean.getPriceMonth().isEmpty()){
                holder.lil_amount_postion_2.setVisibility(View.VISIBLE);
            }else{
                holder.lil_amount_postion_1.setVisibility(View.VISIBLE);
                if (macRentOutPoBean.getPriceDay().isEmpty()) {
                    holder.lil_product_price_1.setVisibility(View.GONE);
                }else{
                    holder.txt_product_price_1.setText(macRentOutPoBean.getPriceDay());
                }
                if (macRentOutPoBean.getPriceHour().isEmpty()) {
                    holder.lil_product_price_2.setVisibility(View.GONE);
                }else{
                    holder.txt_product_price_2.setText(macRentOutPoBean.getPriceHour());
                }
                if (macRentOutPoBean.getPriceMonth().isEmpty()) {
                    holder.lil_product_price_3.setVisibility(View.GONE);
                }else{
                    holder.txt_product_price_3.setText(macRentOutPoBean.getPriceMonth());
                }
            }
        }else{
            holder.txt_product_price.setText(macRentOutPoBean.getProductPrice());
        }



        holder.spr_cart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onAddOrderMessage.addOrderMessage(position, null, null, context.getResources().getStringArray(R.array.paifangbiaozhun)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.edt_count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int count = Integer.parseInt(s.toString());
                if(macRentOutPoBean.getProductType().equals("1")) {

                }else{
                    onAddOrderMessage.addOrderMessage(position, count + "", null, null);
                }

//                holder.txt_yujiprice.setText();
            }
        });

        holder.edt_remarks.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onAddOrderMessage.addOrderMessage(position,null,s.toString(),null);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageview)
        ImageView imageview;

        @BindView(R.id.txt_cart_product_title)
        TextView txt_cart_product_title;
        @BindView(R.id.txt_cart_product_type)
        TextView txt_cart_product_type;
        @BindView(R.id.txt_gerenqiye)
        TextView txt_gerenqiye;
        @BindView(R.id.txt_yujiprice)
        TextView txt_yujiprice;
        @BindView(R.id.edt_count)
        EditText edt_count;
        @BindView(R.id.edt_remarks)
        EditText edt_remarks;
        @BindView(R.id.spr_cart)
        Spinner spr_cart;

        @BindView(R.id.lil_amount_postion)
        LinearLayout lil_amount_postion;
        @BindView(R.id.lil_amount_postion_1)
        LinearLayout lil_amount_postion_1;
        @BindView(R.id.lil_amount_postion_2)
        LinearLayout lil_amount_postion_2;

        @BindView(R.id.lil_product_price_1)
        LinearLayout lil_product_price_1;
        @BindView(R.id.lil_product_price_2)
        LinearLayout lil_product_price_2;
        @BindView(R.id.lil_product_price_3)
        LinearLayout lil_product_price_3;

        @BindView(R.id.txt_product_price)
        TextView txt_product_price;
        @BindView(R.id.txt_product_price_1)
        TextView txt_product_price_1;
        @BindView(R.id.txt_product_price_2)
        TextView txt_product_price_2;
        @BindView(R.id.txt_product_price_3)
        TextView txt_product_price_3;



        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

    public interface OnAddOrderMessage{
        void addOrderMessage(int postion,String count,String remarks,String productPriceUint);
    }

}
