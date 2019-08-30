package com.ssd.yiqiwa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.chengzhu.ChengZhuPublishActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSPublishActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZPublishActivity;
import com.ssd.yiqiwa.ui.activities.publish.ChengZuPublishActivity;
import com.ssd.yiqiwa.ui.activities.publish.MyPublishActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class NavPublishFragment extends BaseFragment {



    public static NavPublishFragment newInstance() {
        NavPublishFragment fragment = new NavPublishFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int offerLayout() {
        return R.layout.fragment_navigation_publish;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void destory() {

    }


    @OnClick({R.id.rlt_fabu_01,R.id.rlt_fabu_02,R.id.rlt_fabu_03,R.id.rlt_fabu_04,R.id.rlt_fabu_05,R.id.rlt_fabu_06,R.id.txt_my_publish})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.rlt_fabu_01:  //承租发布
                startActivity(new Intent(getActivity(), ChengZhuPublishActivity.class));
                break;
            case R.id.rlt_fabu_02: //出租发布
                startActivity(new Intent(getActivity(), CZPublishActivity.class));
                break;
            case R.id.rlt_fabu_03: //二手出售
                startActivity(new Intent(getActivity(), CSPublishActivity.class));
                break;
            case R.id.rlt_fabu_04: //二手购买
                startActivity(new Intent(getActivity(), ChengZuPublishActivity.class));
                break;
            case R.id.rlt_fabu_05: //机主招聘发布

                break;
            case R.id.rlt_fabu_06: //操作手应聘发布

                break;
            case R.id.txt_my_publish:
                MyPublishActivity.start(getActivity(),0);
                break;
        }
    }
}
