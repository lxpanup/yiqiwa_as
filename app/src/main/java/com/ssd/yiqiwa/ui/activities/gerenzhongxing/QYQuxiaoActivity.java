package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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


public class QYQuxiaoActivity extends BaseActivity {


    @BindView(R.id.txt_order_no)
    TextView txt_order_no;
    @BindView(R.id.edit_yyxm)
    EditText edit_yyxm;

    private String orderNo;
    private String osId;

    public static void start(Context context){
        Intent intent=new Intent(context, QYQuxiaoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_qyqx;
    }

    @Override
    public void onBindView() {
        orderNo = getIntent().getStringExtra("orderNo");
        osId = getIntent().getStringExtra("osId");
        txt_order_no.setText(orderNo);


    }

    @Override
    public void destory() {

    }



    @OnClick({R.id.img_back,R.id.txt_qyjl_commint})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_qyjl_commint:
                getuUerAddBankCard();
                break;
        }
    }




    /**
     * 修改用户信息
     */
    public void getuUerAddBankCard(){
        String remark = edit_yyxm.getText().toString().trim();
        if(remark.isEmpty()){
            ToastUtils.showShort("请输入原因");
            return;
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.orderOpOrder(SPStaticUtils.getInt(Constants.SP_USER_ID),osId,"","",remark,"4");

//        uId,osId,remark,managerFlowNo,managerConfirmPic,status
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


