package com.ssd.yiqiwa.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.chushou.CSDetailActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZDetailActivity;
import com.ssd.yiqiwa.utils.Constants;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

public class HomeQuickAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> {

    public HomeQuickAdapter() {
        super(R.layout.item_home_type_recommend);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean item) {

        StringBuilder sb = new StringBuilder();
        sb.append(item.getProvince()+item.getCity());
        if(!item.getFactoryDate().isEmpty()){
            sb.append("|");
            sb.append(item.getFactoryDate().substring(0,4));
        }

        if(!item.getWorkHour().isEmpty()){
            sb.append("|");
            sb.append(item.getWorkHour()+item.getWorkHour());
        }

        helper.setText(R.id.txt_product_name, item.getTitle())
                .setText(R.id.txt_product_category, sb.toString())
                .setText(R.id.txt_product_price, item.getPrice()+"")
                .setText(R.id.txt_product_priceuint, "元/"+item.getPriceUint());

        if(item.getType()==1) {
            helper.setImageResource(R.id.img_product_type,R.mipmap.ic_chuzhu);
        }else{
            helper.setImageResource(R.id.img_product_type,R.mipmap.ic_chushou);
        }

        String coverImage = item.getCoverImage()==null?"":item.getCoverImage();
        Glide.with(mContext).load(Constants.ALIYUN_IMAGE_SSO+coverImage).into((ImageView) helper.getView(R.id.img_product_coverimage));
        helper.getView(R.id.lil_item_product).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                if(item.getType()==1) {
                    intent.setClass(mContext,CZDetailActivity.class);
                }else{
                    intent.setClass(mContext, CSDetailActivity.class);
                }
                intent.putExtra("productRoId",item.getId()+"");
                startActivity(intent);
            }
        });
    }
}
