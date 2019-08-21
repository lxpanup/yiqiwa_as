package com.ssd.yiqiwa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssd.yiqiwa.R;

import butterknife.BindView;
import butterknife.OnClick;


public class NavPublishFragment extends Fragment {



    public static NavPublishFragment newInstance() {
        NavPublishFragment fragment = new NavPublishFragment();
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
        return inflater.inflate(R.layout.fragment_navigation_publish, container, false);
    }



    @OnClick({R.id.rlt_fabu_01,R.id.rlt_fabu_02,R.id.rlt_fabu_03,R.id.rlt_fabu_04,R.id.rlt_fabu_05,R.id.rlt_fabu_06})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.rlt_fabu_01:

                break;
            case R.id.rlt_fabu_02:

                break;
            case R.id.rlt_fabu_03:

                break;
            case R.id.rlt_fabu_04:

                break;
            case R.id.rlt_fabu_05:

                break;
            case R.id.rlt_fabu_06:

                break;
        }
    }
}
