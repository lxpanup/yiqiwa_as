package com.ssd.yiqiwa.ui.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.model.entity.HomeBaseBean;
import com.ssd.yiqiwa.model.entity.ProductBean;

import java.util.List;

public class HomeMultipleAdapter extends BaseMultiItemQuickAdapter<HomeBaseBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeMultipleAdapter(List<HomeBaseBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBaseBean item) {

    }
}
