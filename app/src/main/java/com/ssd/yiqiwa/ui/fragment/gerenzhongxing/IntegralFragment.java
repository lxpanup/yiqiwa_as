package com.ssd.yiqiwa.ui.fragment.gerenzhongxing;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.adapter.IntegralAdapter;
import com.ssd.yiqiwa.ui.adapter.PublicshProductAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class IntegralFragment extends BaseFragment {


    @BindView(R.id.recy_cz_list)
    RecyclerView recy_cz_list;

    @Override
    protected int offerLayout() {
        return R.layout.fb_fragment_cz;
    }

    @Override
    public void onBindView() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recy_cz_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_cz_list.setAdapter(new IntegralAdapter(getActivity(),list));
    }

    @Override
    public void destory() {

    }




}

