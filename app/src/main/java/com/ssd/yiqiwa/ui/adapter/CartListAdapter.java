package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.other.MessageChatActivity;

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
    public List<String> mList;


    public CartListAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mList.size() == 0 ? 1 : mList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {



        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }
}
