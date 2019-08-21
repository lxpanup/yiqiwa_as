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
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.SettingActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.UpdateUserActivity;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class NavMyFragment extends Fragment {

    public static NavMyFragment newInstance() {
        NavMyFragment fragment = new NavMyFragment();
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
        View view = inflater.inflate(R.layout.z_fragment_navigation_my, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public void initView() {



    }


    @OnClick({R.id.img_my_setting,R.id.img_my_head})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_my_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));

                break;
            case R.id.img_my_head:
                startActivity(new Intent(getActivity(), UpdateUserActivity.class));

                break;
        }
    }


}
