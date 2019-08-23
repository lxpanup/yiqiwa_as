package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.ImageUploadAdapter;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GlideLoadEngine;
import com.youth.banner.Banner;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 出租发布
 */
public class CZPublishActivity extends BaseActivity {

    @BindView(R.id.grv_image_upload)
    GridView gridView;



    ImageUploadAdapter imageUploadAdapter;
    private Activity activity;
    private Context context;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_publish;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();
        mSelected = new ArrayList<>();
        mSelected.add(null);
        imageUploadAdapter = new ImageUploadAdapter(mSelected,context);
        gridView.setAdapter(imageUploadAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showMatisse();
            }
        });

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


    public void showMatisse(){
        Matisse.from(this).choose(MimeType.ofImage(), false)
            .countable(true)
                .maxSelectable(21)
                .gridExpectedSize((int) getResources().getDimension(R.dimen.grid_expected_size))
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.87f)
                .imageEngine(new GlideLoadEngine())
            .forResult(REQUEST_CODE_CHOOSE);
    }


    List<Uri> mSelected;
    private final int REQUEST_CODE_CHOOSE = 10001;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected.clear();
            mSelected.addAll(Matisse.obtainResult(data));
            mSelected.add(null);
            Log.e("Matisse", "mSelected: " + mSelected);
            imageUploadAdapter.notifyDataSetChanged();
        }
        Log.e("Matisse", "mSelected: " + data);
    }



}
