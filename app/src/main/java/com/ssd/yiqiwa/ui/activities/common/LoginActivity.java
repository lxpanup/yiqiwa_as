package com.ssd.yiqiwa.ui.activities.common;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.LoginUserBean;
import com.ssd.yiqiwa.ui.activities.MainActivity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.utils.Constants;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends BaseActivity  {


    @BindView(R.id.tbs_login)
    TabLayout tbs_login;
    @BindView(R.id.rlt_logo_show)
    RelativeLayout rlt_logo_show;
    @BindView(R.id.lil_login_register)
    LinearLayout lil_login_register;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.edit_pwd)
    EditText edit_pwd;

    @Override
    public Object offerLayout() {
        return R.layout.z_activity_login;
    }

    @Override
    public void onBindView() {

//        hideActionBar();
        //根据字体长度
        tbs_login.setTabIndicatorFullWidth(false);

        tbs_login.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0) {
                    rlt_logo_show.setVisibility(View.VISIBLE);
                    lil_login_register.setVisibility(View.GONE);
                }else{
                    rlt_logo_show.setVisibility(View.GONE);
                    lil_login_register.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_login,R.id.txt_login_forget})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_login:
                getLogin();
                break;
            case R.id.txt_login_forget:
                startActivity(new Intent(LoginActivity.this,ForgetPwdActivity.class));
                break;
        }
    }





    public void getLogin(){
        String userPhone = edit_phone.getText().toString();
        String userPwd = edit_pwd.getText().toString();
        if(!RegexUtils.isMobileSimple(userPhone)){
            ToastUtils.showLong("手机号错误");
            return;
        }
        if(userPwd.isEmpty()){
            ToastUtils.showLong("密码不能为空");
            return;
        }
        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<LoginUserBean>> call = request.login("18382496398","12345678");
        call.enqueue(new Callback<BaseBean<LoginUserBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<LoginUserBean>> call, Response<BaseBean<LoginUserBean>> response) {
                System.out.println("翻译是："+ response.body().getData().getNickName());
                hideDialog();
                BaseBean<LoginUserBean> beanBaseBean = response.body();
                if(beanBaseBean.getCode()==Constants.HTTP_RESPONSE_OK){
                    LoginUserBean loginUserBean = beanBaseBean.getData();
                    SPStaticUtils.put(Constants.SP_USER_ID,loginUserBean.getuId());
                    SPStaticUtils.put(Constants.SP_USER_NICKNAME,loginUserBean.getNickName());
                    SPStaticUtils.put(Constants.SP_USER_LOGINPHONE,loginUserBean.getLoginPhone());
                    SPStaticUtils.put(Constants.SP_USER_PASSWORD,userPwd);
                    SPStaticUtils.put(Constants.SP_USER_PORTRAIT,loginUserBean.getPortrait());
                    SPStaticUtils.put(Constants.SP_USER_TYPE,loginUserBean.getType());
                    SPStaticUtils.put(Constants.SP_USER_BIRTHDAY,loginUserBean.getBirthday());
                    SPStaticUtils.put(Constants.SP_USER_TOTALSCORE,loginUserBean.getTotalScore());
                    SPStaticUtils.put(Constants.SP_USER_LEFTSCORE,loginUserBean.getLeftScore());
                    SPStaticUtils.put(Constants.SP_USER_MYCODE,loginUserBean.getMyCode());
                    SPStaticUtils.put(Constants.SP_USER_CARDNUMBER,loginUserBean.getCardNumber());
                    SPStaticUtils.put(Constants.SP_USER_CARDBANK,loginUserBean.getCardBank());
                    SPStaticUtils.put(Constants.SP_USER_STATUS,loginUserBean.getStatus());
                    SPStaticUtils.put(Constants.SP_USER_CONTACTPHONE,loginUserBean.getContactPhone());
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    ToastUtils.showLong(beanBaseBean.getMsg());
                }

            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<LoginUserBean>> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });
    }

}
