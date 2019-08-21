package com.ssd.yiqiwa.ui.fragment;

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
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class NavCartFragment extends Fragment {

    @BindView(R.id.recy_cart)
    RecyclerView recy_cart;

    public static NavCartFragment newInstance() {
        NavCartFragment fragment = new NavCartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_navigation_cart, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    public void initView() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recy_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_cart.setAdapter(new CartListAdapter(getActivity(),list));



    }


    @OnClick({R.id.txt_cart_commint})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.txt_cart_commint:
                startActivity(new Intent(getActivity(), CartConfirmActivity.class));
                break;
        }
    }

}

