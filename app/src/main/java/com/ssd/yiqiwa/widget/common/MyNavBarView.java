package com.ssd.yiqiwa.widget.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ssd.yiqiwa.R;

public class MyNavBarView extends LinearLayout {

    private Context con;
    private int inputview_input_icon;
    private String inputview_input_hint;
    private boolean inputview_is_pass;

    private int right_icon;
    private View inflate;
    ImageView imageView;
    TextView editText;
    private View tv_search;

    public MyNavBarView(@NonNull Context context) {
        super(context);
        init(context, null);
        this.con=context;
    }

    public MyNavBarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MyNavBarView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if(attrs==null){
            return;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.mine);
        inputview_input_icon = typedArray.getResourceId(R.styleable.mine_icon, R.mipmap.ic_launcher);
        right_icon = typedArray.getResourceId(R.styleable.mine_icon, R.mipmap.ic_launcher);
        inputview_input_hint = typedArray.getString(R.styleable.mine_hint);
        typedArray.recycle();

        inflate = LayoutInflater.from(context).inflate(R.layout.z_my_nav_bar_view_bar, this, false);
        //imageView=  (ImageView)inflate.findViewById(R.id.tou);
        editText=  (TextView)inflate.findViewById(R.id.title);
        imageView=  (ImageView)inflate.findViewById(R.id.tou);
        imageView.setImageResource(inputview_input_icon);
        editText.setText(inputview_input_hint);
        //editText.setInputType(inputview_is_pass?);
        addView(inflate);

    }
}
