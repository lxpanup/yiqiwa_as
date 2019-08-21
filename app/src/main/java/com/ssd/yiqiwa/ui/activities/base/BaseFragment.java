package com.ssd.yiqiwa.ui.activities.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.utils.Constants;

import org.xmlpull.v1.XmlPullParser;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.blankj.utilcode.util.Utils.runOnUiThread;

/**
 * Create by luopan on 2019/03/14
 * <p>
 * 此app activity的基类
 * （抽象类的设计，深入理解面向对象的核心思想）
 */
@SuppressLint("Registered")
public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected Activity activity;
    private View mConvertView;
    private Unbinder mBinder;

    private ProgressDialog dialog;


    private Retrofit retrofit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mConvertView = inflater.inflate(offerLayout(), container, false);
        activity = getActivity();
        context = activity.getApplicationContext();
        ButterKnife.bind(this, mConvertView);
        if (mConvertView == null) {
            // 传其他资源id 时的处理（只能传布局资源id ）
            throw new ClassCastException("view convert fail，check your resource id  be layout resource");
        } else {
            mBinder = ButterKnife.bind(getActivity());
            onBindView();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL) // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setMessage("正在请求...");
        return mConvertView;
    }


    /**
     * 提供布局
     * 可以为 资源id
     * 可以为 view
     */
    protected abstract int offerLayout();

    /**
     * 处理view。 用户做自己的工作
     */
    public abstract void onBindView();

    public abstract void destory();


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
        Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
    }


    public Retrofit getRetrofit() {
        return retrofit;
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
}
