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

import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.SharedPreferencesUtils;

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

    private CartListAdapter cartListAdapter;
    private List<CartProductBean> cartProductBeanList;

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

        cartProductBeanList = SharedPreferencesUtils.getListData(Constants.SP_CART_LIST_MESSAGE, CartProductBean.class);
        cartListAdapter = new CartListAdapter(getActivity(),cartProductBeanList);
        recy_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_cart.setAdapter(cartListAdapter);

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

