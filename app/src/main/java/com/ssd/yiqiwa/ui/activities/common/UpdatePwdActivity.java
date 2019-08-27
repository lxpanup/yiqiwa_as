package com.ssd.yiqiwa.ui.activities.common;

import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.RegexUtils;
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

public class UpdatePwdActivity extends BaseActivity {


    @BindView(R.id.edt_oldpwd)
    EditText edt_oldpwd;
    @BindView(R.id.edt_newpwd)
    EditText edt_newpwd;
    @BindView(R.id.edt_affirmpwd)
    EditText edt_affirmpwd;


    @Override
    public Object offerLayout() {
        return R.layout.z_activity_updatepwd;
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
       String oldpwd = edt_oldpwd.getText().toString();
        String newpwd = edt_newpwd.getText().toString();
        String affirmpwd = edt_affirmpwd.getText().toString();
        if(!newpwd.equals(affirmpwd)){
            ToastUtils.showLong("确认密码不一致");
            return;
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.userResetPassword(SPStaticUtils.getInt(Constants.SP_USER_ID),oldpwd,newpwd);
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
