package com.ssd.yiqiwa.ui.activities.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.utils.Constants;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by luopan on 2019/03/14
 * <p>
 * 此app activity的基类
 * （抽象类的设计，深入理解面向对象的核心思想）
 */
@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {
    private View mConvertView;
    private Unbinder mBinder;

    private ProgressDialog dialog;
    private Retrofit retrofit;

    /**
     * 加 final 不让子类 重写 onCreate，让其实现我们想要让他实现的方法
     */
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mConvertView = convertView();

        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("正在请求...");

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        if (mConvertView == null) {
            // 传其他资源id 时的处理（只能传布局资源id ）
            throw new ClassCastException("view convert fail，check your resource id  be layout resource");
        } else {
            setContentView(mConvertView);
            mBinder = ButterKnife.bind(this);
            onBindView();
        }
        //沉浸式状态栏
        setStatusBar();

    }

    /**
     * 提供布局
     * 可以为 资源id
     * 可以为 view
     */
    public abstract Object offerLayout();

    /**
     * 处理view。 用户做自己的工作
     */
    public abstract void onBindView();

    public abstract void destory();

    /**
     * view的处理
     * 实现类 传布局资源ID 或者传view
     * 此处进行统一处理
     */
    private View convertView() {
        View view = null;
        if (offerLayout() instanceof Integer) {
            view = LayoutInflater.from(this).inflate((Integer) offerLayout(), null, false);
        } else if (offerLayout() instanceof View) {
            view = (View) offerLayout();
        } else {
            throw new IllegalArgumentException("offerLayout only be View or be Resource Id");
        }
        return view;
    }



    /**
     * 显示加载框
     */
    public void showDialog() {
        showDialog(null);
    }

    /**
     * 显示加载框
     *
     * @param message message
     */
    public void showDialog(String message) {
        if (!TextUtils.isEmpty(message)) {
            dialog.setMessage(message);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.show();
            }
        });

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                hideDialog();
                t.cancel();
            }
        }, 50000);
    }

    /**
     * 隐藏加载框
     *
     */
    public void hideDialog() {
        if (dialog.isShowing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            });
        }
    }

    /**
     * toast显示
     *
     * @param content content
     */
    public void showToast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }

    /**
     * ButterKnife的解绑
     */
    @Override
    public void onDestroy() {
        // 解绑
        if (mBinder != null) {
            mBinder.unbind();
            mBinder = null;
        }
        mConvertView = null; // call gc
        super.onDestroy();
        destory();
    }

    /**
     * 全屏 且隐藏标题栏
     * （子类需要直接使用）
     */
    public void setNoTitleBarAndFullScreen() {
        StatusBarUtil.setTransparent(this);
    }

    /**
     * 沉浸式状态栏
     */
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
    }


    /**
     * 隐藏actionbar
     * */
    public void hideActionBar(){
        getSupportActionBar().hide();
    }

    /**
     * 基础请求访问地址
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }
}
