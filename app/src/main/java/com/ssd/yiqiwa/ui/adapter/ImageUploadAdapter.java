package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.luck.picture.lib.entity.LocalMedia;
import com.ssd.yiqiwa.R;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ImageUploadAdapter extends BaseAdapter {
    private List<LocalMedia> aData;
    private Context mContext;

    private OnClickImageDelete onClickImageDelete;


    public ImageUploadAdapter(List<LocalMedia> aData, Context mContext, OnClickImageDelete onClickImageDelete) {
        this.aData = aData;
        this.mContext = mContext;
        this.onClickImageDelete = onClickImageDelete;
    }

    @Override
    public int getCount() {
        return aData.size()+1;
    }

    @Override
    public LocalMedia getItem(int position) {
        if(position < aData.size()) {
            return aData.get(position);
        }else{
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_image_upload_gridview, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            holder.txtDelete = (TextView) convertView.findViewById(R.id.txt_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (getItem(position) == null) {
            holder.imageView.setImageResource(R.mipmap.ic_image_upload);
            holder.txtDelete.setVisibility(View.GONE);
            holder.imageView.setOnClickListener(v -> onClickImageDelete.onShowPhoto());
        } else {
            holder.txtDelete.setVisibility(View.VISIBLE);
            Glide.with(mContext)
                    .load(getItem(position).getPath())
                    .into(holder.imageView);
            holder.txtDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickImageDelete.onClickImageDelete(position);
                }
            });
        }
        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView txtDelete;
    }

    public interface OnClickImageDelete {
        void onClickImageDelete(int postion);
        void onShowPhoto();
    }

}