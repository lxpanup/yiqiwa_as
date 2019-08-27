package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.FragmentTabAdapter;
import com.ssd.yiqiwa.ui.fragment.publish.CZPublishFragment;
import com.ssd.yiqiwa.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 反馈意见
 */
public class FeedbackActivity extends BaseActivity {


    private Activity activity;
    private Context context;

    @BindView(R.id.edt_conent)
    EditText edt_conent;
    @BindView(R.id.edt_phone)
    EditText edt_phone;

    public static void start(Context context){
        Intent intent=new Intent(context, FeedbackActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_feedback;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_submit})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_submit:
                getUserFeedBack();
                break;

        }
    }



    /**
     *
     */
    public void getUserFeedBack(){
        String content = edt_conent.getText().toString();
        String phone = edt_phone.getText().toString();
        if(content.length()>3&&content.length()<1000){
            ToastUtils.showLong("内容不规范");
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.userFeedBack(SPStaticUtils.getInt(Constants.SP_USER_ID),content,phone);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                ToastUtils.showLong( response.body().getMsg());
                if(response.body().getCode()==Constants.HTTP_RESPONSE_OK) {
                    finish();
                }

            }

            //请求失败时回调
            @Override
            public void onFailure(Call<JsonEntity> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }





}
