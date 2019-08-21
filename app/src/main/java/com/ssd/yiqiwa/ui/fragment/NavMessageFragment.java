package com.ssd.yiqiwa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.adapter.HomeAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Smile Wei
 * @since 2017/3/2.
 */

public class NavMessageFragment extends Fragment {


    @BindView(R.id.recy_message)
    RecyclerView recy_message;

    public static NavMessageFragment newInstance() {
        NavMessageFragment fragment = new NavMessageFragment();
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


        View view = inflater.inflate(R.layout.fragment_navigation_message, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    public void initView() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        recy_message.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_message.setAdapter(new MessageListAdapter(getActivity(),list));



    }
}
