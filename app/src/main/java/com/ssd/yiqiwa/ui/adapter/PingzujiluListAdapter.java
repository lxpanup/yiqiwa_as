package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.PingzujiluDetailedActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.YuyueDetailedActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by SunnyDay on 2019/03/21
 * <p>
 *  凭租记录
 */
public class PingzujiluListAdapter extends RecyclerView.Adapter<PingzujiluListAdapter.MyHolder> {
    private Context context;
    private MainActivity activity;
    public List<String> mList;


    public PingzujiluListAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pingzujilu_list, parent, false);
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

        @OnClick({R.id.lil_item_product})
        public void onViewClick(View view){
            switch (view.getId()){
                case R.id.lil_item_product:
                    PingzujiluDetailedActivity.start(context);
                    break;
            }
        }
    }
}
