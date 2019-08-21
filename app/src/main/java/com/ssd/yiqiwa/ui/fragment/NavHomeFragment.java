package com.ssd.yiqiwa.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.model.entity.HomeBase;
import com.ssd.yiqiwa.ui.adapter.HomeAdapter;
import com.ssd.yiqiwa.widget.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Smile Wei
 * @since 2017/03/01.
 */

public class NavHomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        LoadMoreRecyclerView.OnLoadMoreListener {


    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    LoadMoreRecyclerView recyclerView;

    HomeAdapter adapter;
    private Context context;
    private Activity activity;

    private List<HomeBase> list = new ArrayList<>();

    public static NavHomeFragment newInstance() {
        NavHomeFragment fragment = new NavHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = getActivity();
        context = activity.getApplicationContext();

        View view = inflater.inflate(R.layout.z_fragment_navigation_home, container, false);
        ButterKnife.bind(this, view);
        initView();

        return view;
    }

    public void initView() {
        refreshLayout.setColorSchemeResources(R.color.font_orange_color);
        refreshLayout.setOnRefreshListener(this);
        int spanCount = getResources().getInteger(R.integer.grid_span_count);

        GridLayoutManager layoutManager = new GridLayoutManager(activity, spanCount);
        getTestList();
        adapter = new HomeAdapter(context, activity,list);
        layoutManager.setSpanSizeLookup(adapter.getSpanSizeLookup());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnLoadMoreListener(this);



    }



    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }



    private void getTestList(){
        //模拟返回数据
        int spanCount = 300;

        //list添加轮播图片
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_CAROUSEL, spanCount));
        //list添加分类
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_CATEGORY, spanCount));
        //list
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_HEADLINE, spanCount));

        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_DIVIDER, spanCount));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        list.add(new HomeBase(0, 0, "", "", HomeBase.TYPE_RECOMMEND, 150));
        //list添加头部
//        list.add(new HomeBase(0, 0, "", "热门直播", HomeBase.TYPE_HEADER, spanCount));



    }
}
