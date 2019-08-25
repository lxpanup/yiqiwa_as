package com.ssd.yiqiwa.ui.fragment.jizhu;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.adapter.IntegralAdapter;
import com.ssd.yiqiwa.ui.adapter.JizhuzhaopingAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class JizhuzhaopingFragment extends BaseFragment {


    @BindView(R.id.recy_jz_list)
    RecyclerView myRecycler;

    @Override
    protected int offerLayout() {
        return R.layout.jizhu_fragment_jizhuzhaoping;
    }

    @Override
    public void onBindView() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        myRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecycler.setAdapter(new JizhuzhaopingAdapter(getActivity(),list));
    }

    @Override
    public void destory() {

    }




}

