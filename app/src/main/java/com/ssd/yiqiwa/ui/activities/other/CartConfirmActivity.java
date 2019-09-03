package com.ssd.yiqiwa.ui.activities.other;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.ssd.yiqiwa.R;
import com.ssd.yiqiwa.api.Api;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.CartProductBean;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.ui.activities.base.BaseActivity;
import com.ssd.yiqiwa.ui.adapter.CartConfirmAdapter;
import com.ssd.yiqiwa.ui.adapter.CartListAdapter;
import com.ssd.yiqiwa.ui.adapter.MessageListAdapter;
import com.ssd.yiqiwa.utils.Constants;
import com.ssd.yiqiwa.utils.SharedPreferencesUtils;
import com.ssd.yiqiwa.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 *
 */
public class CartConfirmActivity extends BaseActivity {


    @BindView(R.id.recy_cart_confirm)
    RecyclerView recy_cart_confirm;

    private Activity activity;
    private Context context;


    private CartConfirmAdapter cartListAdapter;
    private List<CartProductBean> cartProductBeanList;
    private List<CartProductBean> listData;

    @Override
    public Object offerLayout() {
        return R.layout.activity_cart_confirm;
    }

    @Override
    public void onBindView() {
        activity = this;
        context = getApplicationContext();

        recy_cart_confirm.setLayoutManager(new LinearLayoutManager(activity));

        cartProductBeanList = new ArrayList<>();
        listData = SharedPreferencesUtils.getListData(Constants.SP_CART_LIST_MESSAGE, CartProductBean.class);
        for(CartProductBean item:listData){
            if(item.isCartCheckbox()){
                cartProductBeanList.add(item);
            }
        }

        cartListAdapter = new CartConfirmAdapter(activity,cartProductBeanList,this::addOrderMessage);
        recy_cart_confirm.setAdapter(cartListAdapter);
    }


    @Override
    public void destory() {

    }

    @OnClick({R.id.img_back,R.id.txt_cart_commint})
    public void onViewClick(View v){
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
            case R.id.txt_cart_commint:
                getOrderProduceOrderJson();
                break;
        }
    }

    /**
     * 添加订单信息
     * @param postion
     * @param count
     * @param remarks
     * @param productPriceUint
     */
    public void addOrderMessage(int postion,String count,String remarks,String productPriceUint){
        CartProductBean cartProductBean = cartProductBeanList.get(postion);
        if(null!=count) {
            cartProductBean.setProductCount(count);
        }else{
            cartProductBean.setProductCount("1");
        }
        if(null!=remarks) {
            cartProductBean.setProductRemarks(remarks);
        }
        if(null!=productPriceUint) {
            cartProductBean.setProductPriceUint(productPriceUint);
        }
    }


    /**
     * 提交预约
     */
    public void getOrderProduceOrderJson(){

        List<HashMap<String,String>> hashMapList = new ArrayList<>();
        HashMap<String,String> hashMap;
        for(CartProductBean item:cartProductBeanList){
            if(item.getProductCount().isEmpty()||Integer.parseInt(item.getProductCount())<=0){
                ToastUtils.showShort(item.getProductTile()+"数量不能小于1");
                return;
            }
            hashMap = new HashMap<>();
            hashMap.put("userId", SPStaticUtils.getInt(Constants.SP_USER_ID)+"");
            hashMap.put("count",item.getProductCount());
            hashMap.put("type",item.getProductType());
            hashMap.put("produceId",item.getProductId());
            hashMap.put("describes",item.getProductRemarks());
            hashMapList.add(hashMap);
        }

        Api request = getRetrofit().create(Api.class);
        String jsonStr = GsonUtils.toJson(hashMapList);
        LogUtils.e(jsonStr);
        Call<JsonEntity> call = request.orderProduceOrderJson(jsonStr);
        call.enqueue(new Callback<JsonEntity>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<JsonEntity> call, Response<JsonEntity> response) {
                hideDialog();
                JsonEntity baseBeanList = response.body();
                if(baseBeanList.getCode()== Constants.HTTP_RESPONSE_OK){
                    ToastUtils.showLong("预约成功,稍后区域经理与你联系");
                    deleteCheckBoxCartList();
                    finish();
                }else{
                    ToastUtils.showLong(baseBeanList.getMsg());
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


    /**
     * 删除购物侧
     */
    private void deleteCheckBoxCartList(){
        List<CartProductBean> list = new ArrayList<>();
        for(CartProductBean item:listData){
            if(!item.isCartCheckbox()){
                list.add(item);
            }
        }
        listData.clear();
        listData.addAll(list);
        SharedPreferencesUtils.putListData(Constants.SP_CART_LIST_MESSAGE,listData);
    }

}
