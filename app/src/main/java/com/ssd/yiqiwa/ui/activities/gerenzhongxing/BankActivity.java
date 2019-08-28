package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.utils.Constants;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BankActivity extends BaseActivity {


    @BindView(R.id.edit_bank_no)
    EditText edit_bank_no;
    @BindView(R.id.edit_bank_name)
    EditText edit_bank_name;
    @BindView(R.id.txt_right_btn)
    TextView txt_right_btn;

    @BindView(R.id.text_bank_no)
    TextView text_bank_no;
    @BindView(R.id.text_bank_name)
    TextView text_bank_name;
    @BindView(R.id.lil_bank_txt)
    LinearLayout lil_bank_txt;
    @BindView(R.id.lil_bank_edit)
    LinearLayout lil_bank_edit;

    public static void start(Context context){
        Intent intent=new Intent(context, BankActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_bank;
    }

    @Override
    public void onBindView() {
        if(SPStaticUtils.getString(Constants.SP_USER_CARDNUMBER).isEmpty()){
            txt_right_btn.setText("取消");
        }else{
            lil_bank_txt.setVisibility(View.VISIBLE);
            lil_bank_edit.setVisibility(View.GONE);
            text_bank_no.setText(SPStaticUtils.getString(Constants.SP_USER_CARDNUMBER));
            text_bank_name.setText(SPStaticUtils.getString(Constants.SP_USER_CARDBANK));
            txt_right_btn.setText("编辑");

        }
    }

    @Override
    public void destory() {

    }



    @OnClick({R.id.img_back,R.id.txt_cart_commint,R.id.txt_right_btn})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_cart_commint:
                getuUerAddBankCard();
                break;
            case R.id.txt_right_btn:
                if(txt_right_btn.getText().toString().equals("编辑")) {
                    lil_bank_txt.setVisibility(View.GONE);
                    lil_bank_edit.setVisibility(View.VISIBLE);
                    edit_bank_no.setText(SPStaticUtils.getString(Constants.SP_USER_CARDNUMBER));
                    edit_bank_name.setText(SPStaticUtils.getString(Constants.SP_USER_CARDBANK));
                    txt_right_btn.setText("取消");
                }else{
                    lil_bank_txt.setVisibility(View.VISIBLE);
                    lil_bank_edit.setVisibility(View.GONE);
                    text_bank_no.setText(SPStaticUtils.getString(Constants.SP_USER_CARDNUMBER));
                    text_bank_name.setText(SPStaticUtils.getString(Constants.SP_USER_CARDBANK));
                    txt_right_btn.setText("编辑");
                }

                break;
        }
    }




    /**
     * 修改用户信息
     */
    public void getuUerAddBankCard(){
        String cardNumber = edit_bank_no.getText().toString();
        String cardBank = edit_bank_name.getText().toString();
        if(cardBank.isEmpty()||cardNumber.isEmpty()){
            ToastUtils.showLong("银行卡信息不能为空");
            return;
        }

        showDialog();
        Api request = getRetrofit().create(Api.class);
        Call<JsonEntity> call = request.userAddBankCard(SPStaticUtils.getInt(Constants.SP_USER_ID),cardNumber,cardBank);
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


