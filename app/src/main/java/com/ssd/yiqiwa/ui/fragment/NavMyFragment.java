package com.ssd.yiqiwa.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.BankActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.CollectActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.IntegralActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.MyJianliActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.PingzujiluListActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.QrCodeActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.SettingActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.UpdateUserActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.YuyueListActivity;
import com.ssd.yiqiwa.ui.activities.jizhu.JizhuListActivity;
import com.ssd.yiqiwa.ui.activities.publish.MyPublishActivity;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class NavMyFragment extends BaseFragment {

    public static NavMyFragment newInstance() {
        NavMyFragment fragment = new NavMyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.z_fragment_navigation_my, container, false);
//        ButterKnife.bind(this, view);
//        initView();
//        return view;
//    }

    @Override
    public int offerLayout() {
        return R.layout.z_fragment_navigation_my;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void destory() {

    }




    @OnClick({R.id.img_my_setting,R.id.img_my_head,R.id.txt_myjianli,R.id.txt_fb_01,R.id.txt_fb_02,R.id.txt_fb_03,R.id.txt_fb_04,
            R.id.txt_fb_05,R.id.txt_fb_06,R.id.lil_my_integral,R.id.lil_my_collect,R.id.lil_my_bank,R.id.txt_qrcode,
            R.id.txt_quyujl_yuyuedingdan,R.id.txt_quyujl_gengjingdindan,R.id.txt_quyujl_lishidingdan,
            R.id.txt_pingzhu_jilu,R.id.txt_goumai_jilu})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_my_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));

                break;
            case R.id.img_my_head:
                startActivity(new Intent(getActivity(), UpdateUserActivity.class));

                break;
            case R.id.txt_myjianli:
                MyJianliActivity.start(getActivity());
                break;
            case R.id.txt_fb_01:
                MyPublishActivity.start(getActivity(),0);
                break;
            case R.id.txt_fb_02:
                MyPublishActivity.start(getActivity(),1);
                break;
            case R.id.txt_fb_03:
                MyPublishActivity.start(getActivity(),2);
                break;
            case R.id.txt_fb_04:
                MyPublishActivity.start(getActivity(),3);
                break;
            case R.id.lil_my_integral:
                IntegralActivity.start(getActivity());
                break;
            case R.id.lil_my_collect:
                CollectActivity.start(getActivity(),0);
                break;
            case R.id.lil_my_bank:
                BankActivity.start(getActivity());
                break;
            case R.id.txt_qrcode:
                QrCodeActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_yuyuedingdan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_gengjingdindan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_lishidingdan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_pingzhu_jilu:
                PingzujiluListActivity.start(getActivity());
                break;
            case R.id.txt_goumai_jilu:
                PingzujiluListActivity.start(getActivity());
                break;


        }
    }


}
