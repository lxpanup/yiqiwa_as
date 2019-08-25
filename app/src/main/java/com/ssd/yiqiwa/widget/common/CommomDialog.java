package com.ssd.yiqiwa.widget.common;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ssd.yiqiwa.R;

import java.net.URL;

public class CommomDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;
    private ImageView dialog_close_imv;


    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;




    public CommomDialog(Context context) {
        super(context, R.style.dialog);
        this.mContext = context;
        this.content = "正在开发对接中请稍等。。。";
        this.listener = new OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {

                dialog.dismiss();
            }
        };

        setNegativeButton("取消");
        setPositiveButton("确认");

    }


    public CommomDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }


    public CommomDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }


    protected CommomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }


    public CommomDialog setTitle(String title){
        this.title = title;
        return this;
    }


    public CommomDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }


    public CommomDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        initView();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView(){
        contentTxt = (TextView)findViewById(R.id.dialog_content_txt);
        titleTxt = (TextView)findViewById(R.id.dialog_title_txt);
        submitTxt = (TextView)findViewById(R.id.yunxu_txt);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.jujue_txt);
        cancelTxt.setOnClickListener(this);
        dialog_close_imv = findViewById(R.id.dialog_close_imv);
        dialog_close_imv.setOnClickListener(this);



        if(!TextUtils.isEmpty(content)) {
            contentTxt.setVisibility(View.VISIBLE);
            contentTxt.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
            contentTxt.setText(Html.fromHtml(content));
        }


        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }


        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }


        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jujue_txt:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.dialog_close_imv:
                if(listener != null){
                    listener.onClick(this, false);
                }
                this.dismiss();
                break;
            case R.id.yunxu_txt:
                if(listener != null){
                    listener.onClick(this, true);
                }
                break;
        }
    }


    public interface OnCloseListener{
        void onClick(Dialog dialog, boolean confirm);
    }
}
