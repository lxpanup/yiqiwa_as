package com.ssd.yiqiwa.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.LoginUserBean;
import com.ssd.yiqiwa.ui.activities.base.BaseFragment;
import com.ssd.yiqiwa.ui.activities.common.LoginActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.BankActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.CollectActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.IntegralActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.MyJianliActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.PingzujiluListActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.QrCodeActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.SettingActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.UpdateUserActivity;
import com.ssd.yiqiwa.ui.activities.gerenzhongxing.YuyueListActivity;
import com.ssd.yiqiwa.ui.activities.jizhu.JizhuListActivity;
import com.ssd.yiqiwa.ui.activities.publish.MyPublishActivity;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NavMyFragment extends BaseFragment {

    @BindView(R.id.img_my_head)
    ImageView img_my_head;
    @BindView(R.id.txt_my_name)
    TextView txt_my_name;
    @BindView(R.id.txt_my_phone)
    TextView txt_my_phone;
    @BindView(R.id.txt_my_jifen)
    TextView txt_my_jifen;
    @BindView(R.id.txt_shouchang)
    TextView txt_shouchang;


    public static NavMyFragment newInstance() {
        NavMyFragment fragment = new NavMyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(SPStaticUtils.getInt(Constants.SP_USER_ID)==-1){
            startActivity(new Intent(getActivity(),LoginActivity.class));
        }else{
            getUserDetail();

        }

    }

    @Override
    public int offerLayout() {
        return R.layout.z_fragment_navigation_my;
    }

    @Override
    public void onBindView() {

    }

    @Override
    public void destory() {

    }






    private void getUserData(){
        if(SPStaticUtils.getString(Constants.SP_USER_NICKNAME).equals("")) {
            txt_my_name.setText("未设置昵称");
        }else{
            txt_my_name.setText(SPStaticUtils.getString(Constants.SP_USER_NICKNAME));
        }
        txt_my_phone.setText(SPStaticUtils.getString(Constants.SP_USER_LOGINPHONE));
        txt_my_jifen.setText(SPStaticUtils.getInt(Constants.SP_USER_TOTALSCORE)+"");
//            txt_shouchang.setText(SPStaticUtils.getString(Constants.SP_USER_NICKNAME));
        Glide.with(getActivity()).load(Constants.ALIYUN_IMAGE_SSO+SPStaticUtils.getString(Constants.SP_USER_PORTRAIT))
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(img_my_head);


    }


    @OnClick({R.id.img_my_setting, R.id.img_my_head, R.id.txt_myjianli, R.id.txt_fb_01, R.id.txt_fb_02, R.id.txt_fb_03, R.id.txt_fb_04,
            R.id.txt_fb_05, R.id.txt_fb_06, R.id.lil_my_integral, R.id.lil_my_collect, R.id.lil_my_bank, R.id.txt_qrcode,
            R.id.txt_quyujl_yuyuedingdan, R.id.txt_quyujl_gengjingdindan, R.id.txt_quyujl_lishidingdan,
            R.id.txt_pingzhu_jilu, R.id.txt_goumai_jilu})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.img_my_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));

                break;
            case R.id.img_my_head:
//                startActivity(new Intent(getActivity(), UpdateUserActivity.class));

                break;
            case R.id.txt_myjianli:
                MyJianliActivity.start(getActivity());
                break;
            case R.id.txt_fb_01:
                MyPublishActivity.start(getActivity(), 0);
                break;
            case R.id.txt_fb_02:
                MyPublishActivity.start(getActivity(), 1);
                break;
            case R.id.txt_fb_03:
                MyPublishActivity.start(getActivity(), 2);
                break;
            case R.id.txt_fb_04:
                MyPublishActivity.start(getActivity(), 3);
                break;
            case R.id.lil_my_integral:
                IntegralActivity.start(getActivity());
                break;
            case R.id.lil_my_collect:
                CollectActivity.start(getActivity(), 0);
                break;
            case R.id.lil_my_bank:
                BankActivity.start(getActivity());
                break;
            case R.id.txt_qrcode:
                QrCodeActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_yuyuedingdan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_gengjingdindan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_quyujl_lishidingdan:
                YuyueListActivity.start(getActivity());
                break;
            case R.id.txt_pingzhu_jilu:
                PingzujiluListActivity.start(getActivity());
                break;
            case R.id.txt_goumai_jilu:
                PingzujiluListActivity.start(getActivity());
                break;


        }
    }




    /**
     * 获取用户详情
     */
    public void getUserDetail(){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<LoginUserBean>> call = request.userDetail(SPStaticUtils.getInt(Constants.SP_USER_ID));
        call.enqueue(new Callback<BaseBean<LoginUserBean>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<LoginUserBean>> call, Response<BaseBean<LoginUserBean>> response) {
                hideDialog();
                BaseBean<LoginUserBean> beanBaseBean = response.body();
                if(beanBaseBean.getCode()==Constants.HTTP_RESPONSE_OK){
                    LoginUserBean loginUserBean = beanBaseBean.getData();
                    SPStaticUtils.put(Constants.SP_USER_ID,loginUserBean.getuId());
                    SPStaticUtils.put(Constants.SP_USER_NICKNAME,loginUserBean.getNickName());
                    SPStaticUtils.put(Constants.SP_USER_LOGINPHONE,loginUserBean.getLoginPhone());
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

                    getUserData();
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
