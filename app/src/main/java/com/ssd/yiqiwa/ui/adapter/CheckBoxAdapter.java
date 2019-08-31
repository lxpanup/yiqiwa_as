package com.ssd.yiqiwa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.luck.picture.lib.entity.LocalMedia;
import com.ssd.yiqiwa.R;

import java.util.List;

public class CheckBoxAdapter extends BaseAdapter {
    private List<String> aData;
    private Context mContext;
    private OnClickCheckChanged onClickCheckChanged;

    public CheckBoxAdapter(List<String> aData, Context mContext, OnClickCheckChanged onClickCheckChanged) {
        this.aData = aData;
        this.mContext = mContext;
        this.onClickCheckChanged = onClickCheckChanged;
    }

    @Override
    public int getCount() {
        return aData.size();
    }

    @Override
    public String getItem(int position) {
        return aData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_checkbox, parent, false);
            holder = new ViewHolder();
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.checkbox.setText(getItem(position));
        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                onClickCheckChanged.onCheckedChanged(position,isChecked);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        CheckBox checkbox;
    }

    public interface OnClickCheckChanged {
        void onCheckedChanged(int postion,boolean isCheck);
    }

}