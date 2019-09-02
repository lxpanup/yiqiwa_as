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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.contract.HomeContract;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.model.entity.ProductBean;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.other.CartConfirmActivity;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter.OnCartCheckbox;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.SharedPreferencesUtils;
import com.ssd.yiqiwa.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class NavCartFragment extends BaseFragment {

    @BindView(R.id.recy_cart)
    RecyclerView recy_cart;

    @BindView(R.id.cbx_cart_isall)
    CheckBox cbx_cart_isall;

    @BindView(R.id.img_head_right)
    TextView img_head_right;

    @BindView(R.id.txt_cart_delete)
    TextView txt_cart_delete;

    private CartListAdapter cartListAdapter;
    private List<CartProductBean> cartProductBeanList;

    public static NavCartFragment newInstance() {
        NavCartFragment fragment = new NavCartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected int offerLayout() {
        return R.layout.fragment_navigation_cart;
    }

    @Override
    public void onBindView() {
        cartProductBeanList = SharedPreferencesUtils.getListData(Constants.SP_CART_LIST_MESSAGE, CartProductBean.class);
        cartListAdapter = new CartListAdapter(getActivity(),cartProductBeanList, this::clickCheckboxCartList);
        recy_cart.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_cart.setAdapter(cartListAdapter);


        cbx_cart_isall.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setAllCheckBoxCartList(isChecked);
        });

    }


    @Override
    public void destory() {

    }


    @Override
    public void onResume() {
        super.onResume();
        if(cartListAdapter!=null) {
            cartProductBeanList.clear();
            cartProductBeanList.addAll(SharedPreferencesUtils.getListData(Constants.SP_CART_LIST_MESSAGE, CartProductBean.class));
            cartListAdapter.notifyDataSetChanged();
        }
    }




    @OnClick({R.id.txt_cart_commint,R.id.img_head_right,R.id.txt_cart_delete})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.txt_cart_commint:
                SharedPreferencesUtils.putListData(Constants.SP_CART_LIST_MESSAGE,cartProductBeanList);
                startActivity(new Intent(getActivity(), CartConfirmActivity.class));
                break;
            case R.id.img_head_right:
                if(img_head_right.getText().equals("完成")) {
                    img_head_right.setText("管理");
                    txt_cart_delete.setVisibility(View.GONE);
                }else {
                    img_head_right.setText("完成");
                    txt_cart_delete.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.txt_cart_delete:
                deleteCheckBoxCartList();
                break;
        }
    }

    /**
     * 单击选择购物车列表
     * @param postion
     * @param isChecked
     */
    private void clickCheckboxCartList(int postion, boolean isChecked){
        CartProductBean cartProductBean = cartProductBeanList.get(postion);
        cartProductBean.setCartCheckbox(isChecked);
        cartProductBeanList.set(postion,cartProductBean);
    }

    /**
     * 设置全选选择购物车列表
     * @param isChecked
     */
    private void setAllCheckBoxCartList(boolean isChecked){
        for(int i = 0; i < cartProductBeanList.size(); i++){
            CartProductBean cartProductBean = cartProductBeanList.get(i);
            cartProductBean.setCartCheckbox(isChecked);
            cartProductBeanList.set(i,cartProductBean);
        }
        cartListAdapter.notifyDataSetChanged();
    }


    private void deleteCheckBoxCartList(){
        List<CartProductBean> list = new ArrayList<>();
        for(CartProductBean item:cartProductBeanList){
            if(!item.isCartCheckbox()){
                list.add(item);
            }
        }
        cartProductBeanList.clear();
        cartProductBeanList.addAll(list);
        cartListAdapter.notifyDataSetChanged();
        SharedPreferencesUtils.putListData(Constants.SP_CART_LIST_MESSAGE,cartProductBeanList);
        ToastUtils.showShort("删除成功");
    }


}

