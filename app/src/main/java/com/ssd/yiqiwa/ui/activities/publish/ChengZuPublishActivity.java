package com.ssd.yiqiwa.ui.activities.publish;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
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
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 承租发布
 */
public class ChengZuPublishActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    Validator validator;


    @Override
    public Object offerLayout() {
        return R.layout.fb_activity_chengzu;
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
//                validator.validate();
                verifyPublishDialog();
                break;

        }
    }




    /**
     * 验证信息
     */
    private void verifyPublishDialog(){
        Dialog dia = new Dialog(activity, R.style.dialog);
        dia.setContentView(R.layout.item_fb_verify_baocun);

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
                ToastUtils.showLong("发布成功");
                dia.dismiss();
            }
        });
        dia.findViewById(R.id.txt_jingpingfabu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLong("保存成功");
                dia.dismiss();
            }
        });

        dia.setCanceledOnTouchOutside(false); // Sets whether this dialog is


        dia.show();
    }





}
