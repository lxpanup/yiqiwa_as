package com.ssd.yiqiwa.ui.activities.common;

import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.utils.Constants;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPwdActivity extends BaseActivity {


    @BindView(R.id.edt_register_phone)
    EditText edt_register_phone;
    @BindView(R.id.edt_register_yanzhengma)
    EditText edt_register_yanzhengma;
    @BindView(R.id.edit_register_pwd)
    EditText edit_register_pwd;
    @BindView(R.id.edt_register_confirm_pwd)
    EditText edt_register_confirm_pwd;



    @Override
    public Object offerLayout() {
        return R.layout.z_activity_forgetpwd;
    }

    @Override
    public void onBindView() {

    }


    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_xiugaimima})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_xiugaimima:
                getUserResetPassword();
                break;
        }
    }




    /**
     *
     */
    public void getUserResetPassword(){
        String phone = edt_register_phone.getText().toString();
        String newpwd = edit_register_pwd.getText().toString();
        String affirmpwd = edt_register_confirm_pwd.getText().toString();
        String validCode = edt_register_yanzhengma.getText().toString();
        if(!newpwd.equals(affirmpwd)){
            ToastUtils.showLong("确认密码不一致");
            return;
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.userResetPassword(phone,validCode,newpwd);
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
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
            }
        });
    }

}
