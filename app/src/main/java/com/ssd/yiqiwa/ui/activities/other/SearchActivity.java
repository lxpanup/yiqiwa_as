package com.ssd.yiqiwa.ui.activities.other;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.CartConfirmAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 搜索
 */
public class SearchActivity extends BaseActivity {



    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.z_activity_search;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();




    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }
}
