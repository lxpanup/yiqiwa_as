package com.ssd.yiqiwa.ui.activities.chuzhu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Checked;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.ImageUploadAdapter;
import com.ssd.yiqiwa.widget.GlideImageLoader;
import com.ssd.yiqiwa.widget.GlideLoadEngine;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
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

    Validator validator;

    @NotEmpty(message = "标题不能为空")
    @BindView(R.id.edt_publish_01)
    EditText edt_publish_01;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_03)
    EditText edt_publish_03;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_04)
    EditText edt_publish_04;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_05)
    EditText edt_publish_05;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_07)
    EditText edt_publish_07;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_10)
    EditText edt_publish_10;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_11)
    EditText edt_publish_11;
//    @NotEmpty(message = "不能为空")
//    @BindView(R.id.edt_publish_12)
//    EditText edt_publish_12;
//    @NotEmpty(message = "不能为空")
//    @BindView(R.id.edt_publish_14)
//    EditText edt_publish_14;
//    @NotEmpty(message = "不能为空")
//    @BindView(R.id.edt_publish_15)
//    EditText edt_publish_15;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_16)
    EditText edt_publish_16;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_17)
    EditText edt_publish_17;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_19)
    EditText edt_publish_19;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_20)
    EditText edt_publish_20;
    @NotEmpty(message = "不能为空")
    @BindView(R.id.edt_publish_22)
    EditText edt_publish_22;

    @BindView(R.id.spr_publish_06)
    Spinner spr_publish_06;
    @BindView(R.id.spr_publish_08)
    Spinner spr_publish_08;
    @BindView(R.id.spr_publish_18)
    Spinner spr_publish_18;

    @Override
    public Object offerLayout() {
        return R.layout.cz_activity_publish;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();
        validator = new Validator(this);
        validator.setValidationListener(new Validator.ValidationListener() {
            @Override
            public void onValidationSucceeded() {
                verifyPublishDialog();
            }

            @Override
            public void onValidationFailed(List<ValidationError> errors) {
                for (ValidationError error : errors) {
                    View view = error.getView();
                    String message = error.getCollatedErrorMessage(activity);
                    if (view instanceof EditText) {
                        ((EditText) view).setError(message);
                    }
                }
            }
        });

        mSelected = new ArrayList<>();
        mSelected.add(null);
        imageUploadAdapter = new ImageUploadAdapter(mSelected, context, postion -> {
            mSelected.remove(postion);
            imageUploadAdapter.notifyDataSetChanged();
        });
        gridView.setAdapter(imageUploadAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mSelected.get(position)!=null){
                    photoShowDialog(position);
                }else {
                    showMatisse();
                }
            }
        });

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_verify_publish})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_verify_publish:
                validator.validate();
//                verifyPublishDialog();
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
            mSelected.remove(mSelected.size()-1);

            List<Uri> matisse = Matisse.obtainResult(data);
            boolean isAdd = true;
            for(int i =0; i < matisse.size();i++){
                for(int j =0; j < mSelected.size();j++){
                    Log.e("CZ","matisse:"+matisse.get(i).getPath()+"mSelected:"+ mSelected.get(j).getPath()+"|"+matisse.get(i).getPath().equals(mSelected.get(j).getPath()));
                    if(matisse.get(i).getPath().equals(mSelected.get(j).getPath())){
                        isAdd = false;
                        break;
                    }
                }
                if(isAdd) {
                    mSelected.add(matisse.get(i));
                    isAdd = true;
                }
            }
//            mSelected.addAll(Matisse.obtainResult(data));
            mSelected.add(null);
            Log.e("CZ", "mSelected: " + mSelected.size());
            imageUploadAdapter.notifyDataSetChanged();
        }
        Log.e("Matisse", "mSelected: " + data);
    }




    /**
     * 验证信息
     */
    private void verifyPublishDialog(){
        Dialog dia = new Dialog(activity, R.style.dialog);
        dia.setContentView(R.layout.item_cz_verify_publish);

        ImageView textView = dia.findViewById(R.id.img_close);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_putongfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("普通发布成功");
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_jingpingfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("精品发布成功");
                dia.dismiss();
            }
        });




        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

//        Window window = dia.getWindow();
//        window.getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        layoutParams.horizontalMargin = 0;
//        window.setAttributes(layoutParams);
//        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
//        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }



    /**
     * 查看图片
     */
    private void photoShowDialog(int position){
        Dialog dia = new Dialog(activity, R.style.imageDialog);
        dia.setContentView(R.layout.item_image_banner);
        Banner banner =  dia.findViewById(R.id.banner);
        List<Uri> uriList = mSelected;
//        uriList.remove(uriList.size()-1);
        banner.setImageLoader(new GlideImageLoader())
                .setImages(uriList)
                .isAutoPlay(false)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .start();
        banner.setIndicatorGravity(position);

        ImageView imageView = dia.findViewById(R.id.img_count_down);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dia.dismiss();
            }
        });
        //选择true的话点击其他地方可以使dialog消失，为false的话不会消失
        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is

        Window window = dia.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.horizontalMargin = 0;
        window.setAttributes(layoutParams);
        window.getDecorView().setMinimumWidth(getResources().getDisplayMetrics().widthPixels);
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);

        dia.show();
    }


}
