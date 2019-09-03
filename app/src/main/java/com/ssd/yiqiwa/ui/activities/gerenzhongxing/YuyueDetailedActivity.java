package com.ssd.yiqiwa.ui.activities.gerenzhongxing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.LoginUserBean;
import com.ssd.yiqiwa.model.entity.MacBuyPoBean;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.MacBuyPoBean;
import com.ssd.yiqiwa.model.entity.MacRentIntPoBean;
import com.ssd.yiqiwa.model.entity.MacRentOutPoBean;
import com.ssd.yiqiwa.model.entity.MacSellPoBean;
import com.ssd.yiqiwa.model.entity.OrderDetailBean;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.activities.chengzhu.ChengZhuPublishActivity;
import com.ssd.yiqiwa.ui.activities.chushou.CSDetailActivity;
import com.ssd.yiqiwa.ui.activities.chuzhu.CZDetailActivity;
import com.ssd.yiqiwa.ui.activities.goumai.GoumaiDetailActivity;
import com.ssd.yiqiwa.ui.adapter.YuyueListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.widget.common.CommomDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 预约订单详情
 */
public class YuyueDetailedActivity extends BaseActivity {


    private Activity activity;
    private Context context;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.txt_address)
    TextView txt_address;
    @BindView(R.id.txt_count)
    TextView txt_count;
    @BindView(R.id.txt_price)
    TextView txt_price;
    @BindView(R.id.txt_product_ckxq)
    TextView txt_product_ckxq;


    @BindView(R.id.txt_cz_phone)
    TextView txt_cz_phone;
    @BindView(R.id.txt_cz_address)
    TextView txt_cz_address;
    @BindView(R.id.txt_cz_name)
    TextView txt_cz_name;

    @BindView(R.id.txt_cc_phone)
    TextView txt_cc_phone;
    @BindView(R.id.txt_cc_address)
    TextView txt_cc_address;
    @BindView(R.id.txt_cc_name)
    TextView txt_cc_name;
    @BindView(R.id.txt_bzxx)
    TextView txt_bzxx;

    @BindView(R.id.txt_detailed_status)
    TextView txt_detailed_status;
    @BindView(R.id.txt_gjdd)
    TextView txt_gjdd;


    private String orderNo;
    private String osId;

    public static void start(Context context){
        Intent intent=new Intent(context, YuyueDetailedActivity.class);
        context.startActivity(intent);
    }


    @Override
    public Object offerLayout() {
        return R.layout.grzx_activity_yuyuedetailed;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();
        String productId = getIntent().getStringExtra("productId");
        String ordertype = getIntent().getStringExtra("ordertype");

        showDialog();
        if(ordertype.equals("1")) {
            getOrderMangerReserveOrder1(productId);
        }else if(ordertype.equals("2")) {
            getOrderMangerReserveOrder2(productId);
        }if(ordertype.equals("3")) {
            getOrderMangerReserveOrder3(productId);
        }if(ordertype.equals("4")) {
            getOrderMangerReserveOrder4(productId);
        }
    }

    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_gjdd})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_gjdd:
                gengjingDialogShow();
                break;
        }
    }


    private void gengjingDialogShow(){
        CommomDialog commomDialog = new CommomDialog(YuyueDetailedActivity.this, "跟进结果", "", (dialog, confirm) -> {
            if (confirm) {
                ToastUtils.showShort("订单成功");
            }else{
                Intent intent = new Intent();
                intent.setClass(context,QYQuxiaoActivity.class);
                intent.putExtra("orderNo",orderNo);
                intent.putExtra("osId",osId);
                startActivity(intent);

            }
        });
        commomDialog.setPositiveButton("成功");
        commomDialog.setNegativeButton("失败");
        commomDialog.show();

    }





    /**
     * 购买信息
     * @param productId
     */
    public void getOrderMangerReserveOrder1(String productId){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<OrderDetailBean<MacBuyPoBean>>> call = request.orderMangerReserveOrder(productId);
        call.enqueue(new Callback<BaseBean<OrderDetailBean<MacBuyPoBean>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<OrderDetailBean<MacBuyPoBean>>> call, Response<BaseBean<OrderDetailBean<MacBuyPoBean>>> response) {
                hideDialog();
                BaseBean<OrderDetailBean<MacBuyPoBean>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    MacOrderSubPo macOrderSubPo = baseBeanList.getData().getOrder();
                    MacBuyPoBean macBuyPoBean = baseBeanList.getData().getProduct();
                    txt_title.setText("出租商品："+macBuyPoBean.getTitle());
                    txt_address.setText("地址："+macBuyPoBean.getCity()+" "+macBuyPoBean.getRentFrom());
                    txt_count.setText("购买数量："+macOrderSubPo.getCount());
                    txt_price.setText(macOrderSubPo.getPrice());
                    txt_product_ckxq.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        intent.putExtra("productRoId",macBuyPoBean.getbId());
                        intent.setClass(context, CSDetailActivity.class);
                        startActivity(intent);
                    });
                    txt_cz_phone.setText(macBuyPoBean.getContactPhone());
                    setStatusText(macOrderSubPo.getStatus());
                    setCzUserView(macOrderSubPo);
                    setOrderUserView(baseBeanList.getData().getUser());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<OrderDetailBean<MacBuyPoBean>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                hideDialog();
            }
        });
    }




    /**
     * 出售信息
     * @param productId
     */
    public void getOrderMangerReserveOrder2(String productId){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<OrderDetailBean<MacSellPoBean>>> call = request.orderMangerReserveOrder2(productId);
        call.enqueue(new Callback<BaseBean<OrderDetailBean<MacSellPoBean>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<OrderDetailBean<MacSellPoBean>>> call, Response<BaseBean<OrderDetailBean<MacSellPoBean>>> response) {
                hideDialog();
                BaseBean<OrderDetailBean<MacSellPoBean>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    MacOrderSubPo macOrderSubPo = baseBeanList.getData().getOrder();
                    MacSellPoBean macBuyPoBean = baseBeanList.getData().getProduct();
                    txt_title.setText("出租商品："+macBuyPoBean.getTitle());
                    txt_address.setText("地址："+macBuyPoBean.getCity()+" "+macBuyPoBean.getRentFrom());
                    txt_count.setText("购买数量："+macOrderSubPo.getCount());
                    txt_price.setText(macOrderSubPo.getPrice());
                    txt_product_ckxq.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        intent.putExtra("productRoId",macBuyPoBean.getsId());
                        intent.setClass(context, CSDetailActivity.class);
                        startActivity(intent);
                    });
                    txt_cz_phone.setText(macBuyPoBean.getContactPhone());
                    setStatusText(macOrderSubPo.getStatus());
                    setCzUserView(macOrderSubPo);
                    setOrderUserView(baseBeanList.getData().getUser());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<OrderDetailBean<MacSellPoBean>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                hideDialog();
            }
        });
    }

    /**
     * 出租信息
     * @param productId
     */
    public void getOrderMangerReserveOrder3(String productId){

        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<OrderDetailBean<MacRentOutPoBean>>> call = request.orderMangerReserveOrder3(productId);
        call.enqueue(new Callback<BaseBean<OrderDetailBean<MacRentOutPoBean>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<OrderDetailBean<MacRentOutPoBean>>> call, Response<BaseBean<OrderDetailBean<MacRentOutPoBean>>> response) {
                hideDialog();
                BaseBean<OrderDetailBean<MacRentOutPoBean>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    MacOrderSubPo macOrderSubPo = baseBeanList.getData().getOrder();
                    MacRentOutPoBean macBuyPoBean = baseBeanList.getData().getProduct();
                    txt_title.setText("出租商品："+macBuyPoBean.getTitle());
                    txt_address.setText("地址："+macBuyPoBean.getCity()+" "+macBuyPoBean.getRentFrom());
                    txt_count.setText("购买数量："+macOrderSubPo.getCount());
                    txt_price.setText(macOrderSubPo.getPrice()+macOrderSubPo.getPriceUint());

                    txt_product_ckxq.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        intent.putExtra("productRoId",macBuyPoBean.getRoId());
                        intent.setClass(context, CZDetailActivity.class);
                        startActivity(intent);
                    });
                    txt_cz_phone.setText(macBuyPoBean.getContactPhone());
                    setStatusText(macOrderSubPo.getStatus());
                    setCzUserView(macOrderSubPo);
                    setOrderUserView(baseBeanList.getData().getUser());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<OrderDetailBean<MacRentOutPoBean>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                hideDialog();
            }
        });
    }

    /**
     * 承租信息
     * @param productId
     */
    public void getOrderMangerReserveOrder4(String productId){
        Api request = getRetrofit().create(Api.class);
        Call<BaseBean<OrderDetailBean<MacRentIntPoBean>>> call = request.orderMangerReserveOrder4(productId);
        call.enqueue(new Callback<BaseBean<OrderDetailBean<MacRentIntPoBean>>>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BaseBean<OrderDetailBean<MacRentIntPoBean>>> call, Response<BaseBean<OrderDetailBean<MacRentIntPoBean>>> response) {
                hideDialog();
                BaseBean<OrderDetailBean<MacRentIntPoBean>> baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    MacOrderSubPo macOrderSubPo = baseBeanList.getData().getOrder();
                    MacRentIntPoBean macBuyPoBean = baseBeanList.getData().getProduct();
                    txt_title.setText("出租商品："+macBuyPoBean.getTitle());
                    txt_address.setText("地址："+macBuyPoBean.getCity()+" "+macBuyPoBean.getRentFrom());
                    txt_count.setText("购买数量："+macBuyPoBean.getCount());
                    txt_price.setText(macOrderSubPo.getPrice()+macOrderSubPo.getPriceUint());
                    txt_product_ckxq.setOnClickListener(v -> {
                        Intent intent = new Intent();
                        intent.putExtra("productRoId",macBuyPoBean.getRiId());
                        intent.setClass(context, CSDetailActivity.class);
                        startActivity(intent);
                    });
                    setStatusText(macOrderSubPo.getStatus());
                    setCzUserView(baseBeanList.getData().getOrder());
                    setOrderUserView(baseBeanList.getData().getUser());
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
                }
            }
            //请求失败时回调
            @Override
            public void onFailure(Call<BaseBean<OrderDetailBean<MacRentIntPoBean>>> call, Throwable throwable) {
                LogUtils.e("请求失败");
                LogUtils.e(throwable.getMessage());
                hideDialog();
            }
        });
    }


    /**
     * 接单用户信息
     */
    private void setCzUserView(MacOrderSubPo macOrderSubPo){
        orderNo = macOrderSubPo.getOrderSubNum();
        osId = macOrderSubPo.getOsId();
        txt_cz_address.setText(macOrderSubPo.getProvince()+""+macOrderSubPo.getCity());
        txt_cz_name.setText(macOrderSubPo.getProductTitle());
        txt_bzxx.setText(macOrderSubPo.getRemark());
    }

    /**
     * 接单用户信息
     */
    private void setOrderUserView(LoginUserBean userView){
        txt_cc_phone.setText(userView.getContactPhone());
        txt_cc_name.setText("四川省成都市");
        txt_cc_address.setText(userView.getNickName());
    }


    private void setStatusText(String status){
        if(status.equals("3")){
            txt_detailed_status.setTextColor(getResources().getColor(R.color.colorPrimary));
            txt_detailed_status.setText("订单成功");
        }else if(status.equals("4")){
            txt_detailed_status.setText("订单失败");
            txt_detailed_status.setTextColor(getResources().getColor(R.color.red));
        }else if(status.equals("2")) {
            txt_detailed_status.setVisibility(View.GONE);
            txt_gjdd.setVisibility(View.VISIBLE);
        } else {
            txt_detailed_status.setVisibility(View.GONE);
        }

    }





}
