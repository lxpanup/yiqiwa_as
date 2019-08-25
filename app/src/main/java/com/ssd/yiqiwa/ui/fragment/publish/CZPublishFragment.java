package com.ssd.yiqiwa.ui.fragment.publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.PublicshProductAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class CZPublishFragment extends BaseFragment {


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
        recy_cz_list.setAdapter(new PublicshProductAdapter(getActivity(),list));
    }

    @Override
    public void destory() {

    }




}

