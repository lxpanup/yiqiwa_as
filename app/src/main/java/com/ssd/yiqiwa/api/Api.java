package com.ssd.yiqiwa.api;


import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.LoginUserBean;
import com.ssd.yiqiwa.model.entity.MacBuyPoBean;
import com.ssd.yiqiwa.model.entity.MacOrderSubPo;
import com.ssd.yiqiwa.model.entity.MacRentIntPoBean;
import com.ssd.yiqiwa.model.entity.MacRentOutPoBean;
import com.ssd.yiqiwa.model.entity.MacSellPoBean;
import com.ssd.yiqiwa.model.entity.MachineBrandBean;
import com.ssd.yiqiwa.model.entity.MachineModelBean;
import com.ssd.yiqiwa.model.entity.MachineTypeBean;
import com.ssd.yiqiwa.model.entity.OrderDetailBean;
import com.ssd.yiqiwa.model.entity.PagesBean;
import com.ssd.yiqiwa.model.entity.ProductBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 创建用于描述网络请求的接口
 *
 * @author joe
 */
public interface Api {


    /**
     * 登录
     * @return .
     */
    @POST("login")
    Call<BaseBean<LoginUserBean>> login(@Query("phone") String phone, @Query("password") String password);


    /**
     * 注册
     * @return .
     */
    @POST("register")
    Call<JsonEntity> register(@Query("phone") String phone, @Query("password") String password,
                              @Query("validCode") String validCode, @Query("recommendCode") String recommendCode);


    /**
     * 修改用户信息
     * @return .
     */
    @POST("user/changeInfo")
    Call<JsonEntity> userChangeInfo(@Query("uId") int uId, @Query("portrait") String portrait,
                              @Query("nickName") String nickName, @Query("contactPhone") String contactPhone,
                              @Query("birthday") String birthday);

    /**
     * 修改密码
     * @return .
     */
    @POST("user/resetPassword")
    Call<JsonEntity> userResetPassword(@Query("uId") int uId, @Query("oriPassword") String oriPassword,
                              @Query("newPassword") String newPassword);


    /**
     * 忘记密码
     * @return .
     */
    @POST("user/resetPassword")
    Call<JsonEntity> userResetPassword(@Query("phone") String phone, @Query("validCode") String validCode,
                                       @Query("newPassword") String newPassword);

    /**
     * 修改银行卡
     * @return .
     */
    @POST("user/addBankCard")
    Call<JsonEntity> userAddBankCard(@Query("uId") int uId, @Query("cardNumber") String cardNumber,
                                       @Query("cardBank") String cardBank);

    /**
     * 意见反馈
     * @return .
     */
    @POST("user/feedBack")
    Call<JsonEntity> userFeedBack(@Query("uId") int uId, @Query("content") String content,
                              @Query("contactPhone") String contactPhone);

    /**
     * 用户信息信息
     * @return .
     */
    @POST("user/userDetail")
    Call<BaseBean<LoginUserBean>> userDetail(@Query("uId") int uId);




    /**
     * 首页banner
     * @return .
     */
    @POST("home/banner")
    Call<BaseBeanList<HomeBannerImages>> homeBanner();

    /**
     * 推荐产品-新品推荐
     * @return .
     */
    @POST("home/newProduct")
    Call<BaseBeanList<ProductBean>> homeNewProduct();

    /**
     * 特价专区
     * @return .
     */
    @POST("home/discountZone")
    Call<BaseBeanList<ProductBean>> homeDiscountZone();

    /**
     * 品牌名称
     * @return .
     */
    @POST("machineBrand/all")
    Call<BaseBeanList<MachineBrandBean>> machineBrandAll();

    /**
     * 设备类型
     * @return .
     */
    @POST("machineType/all")
    Call<BaseBeanList<MachineTypeBean>> machineTypeAll();

    /**
     * 设备型号
     * @return .
     */
    @POST("machineModel/type")
    Call<BaseBeanList<MachineModelBean>> machineModelType(@Query("mbId") int mbId,@Query("type") int type);

    /** ******************** 发布信息 **************************** */
    /**
     * 出租发布
     * @return .
     */
    @POST("rentOut/add")
    Call<JsonEntity> rentOutAdd(@QueryMap Map<String, Object> map);

    /**
     * 出售发布
     * @return .
     */
    @POST("sell/add")
    Call<JsonEntity> sellAdd(@QueryMap Map<String, Object> map);

    /**
     * 发布承租信息
     * @return .
     */
    @POST("rentIn/add")
    Call<JsonEntity> rentInAdd(@QueryMap Map<String, Object> map);

    /**
     * 二手购买
     * @return .
     */
    @POST("buy/add")
    Call<JsonEntity> buyAdd(@QueryMap Map<String, Object> map);



    /**
     * 出租列表
     * @return .
     */
    @POST("rentOut/list")
    Call<BaseBean<PagesBean<MacRentOutPoBean>>> rentOutList(@Query("pageNo") int pageNo);

    /**
     * 出租详情
     * @return .
     */
    @POST("rentOut/detail")
    Call<BaseBean<MacRentOutPoBean>> rentOutDetail(@Query("roId") int roId);


    /**
     * 承租列表
     * @return .
     */
    @POST("rentIn/list")
    Call<BaseBean<PagesBean<MacRentIntPoBean>>> rentInList(@Query("pageNo") int pageNo);

    /**
     * 承租详情
     * @return .
     */
    @POST("rentIn/detail")
    Call<BaseBean<MacRentIntPoBean>> rentInDetail(@Query("riId") int roId);

    /**
     * 二手出售列表
     * @return .
     */
    @POST("sell/list")
    Call<BaseBean<PagesBean<MacSellPoBean>>> sellList(@Query("pageNo") int pageNo);

    /**
     * 二手出售详情
     * @return .
     */
    @POST("sell/detail")
    Call<BaseBean<MacSellPoBean>> sellDetail(@Query("sId") int sId);

    /**
     * 二手购买列表
     * @return .
     */
    @POST("buy/list")
    Call<BaseBean<PagesBean<MacBuyPoBean>>> buyList(@Query("pageNo") int pageNo);

    /**
     * 二手购买详情
     * @return .
     */
    @POST("buy/detail")
    Call<BaseBean<MacBuyPoBean>> buyDetail(@Query("bId") int bId);



    /**
     * 我的出租信息
     * @return .
     */
    @POST("user/rentOutInfo")
    Call<BaseBeanList<MacRentOutPoBean>> userRentOutInfo(@Query("uId") int uId);



    /** ******************** 购物车 **************************** */
    /**
     * 订单-生成四种订单-- 1购买 2出售 3承租 4出租 用户而言 JSON 的数据 ,建议用这个
     * @return .
     */
    @POST("order/produceOrderJson")
    Call<JsonEntity> orderProduceOrderJson(@Query("data") String data);



    /** ******************** 区域经理 **************************** */
    /**
     * 区域经理的订单 预约订单
     * @return .
     */
    @POST("order/mangerReserveOrder")
    Call<BaseBean<PagesBean<MacOrderSubPo>>> orderMangerReserveOrder(@Query("uId") int uId, @Query("pageNo") int pageNo);

    /**
     * 区域经理的订单 跟进订单
     * @return .
     */
    @POST("order/mangerFollowOrder")
    Call<BaseBean<PagesBean<MacOrderSubPo>>> orderMangerFollowOrder(@Query("uId") int uId, @Query("pageNo") int pageNo);

    /**
     * 区域经理的订单 历史订单
     * @return .
     */
    @POST("order/mangerHistoryOrder")
    Call<BaseBean<PagesBean<MacOrderSubPo>>> orderMangerHistoryOrder(@Query("uId") int uId, @Query("pageNo") int pageNo);


    /**
     * 区域经理的订单详情-购买
     * @return .
     */
    @POST("order/detail")
    Call<BaseBean<OrderDetailBean<MacBuyPoBean>>> orderMangerReserveOrder(@Query("theId") String theId);

    /**
     * 区域经理的订单详情-出售
     * @return .
     */
    @POST("order/detail")
    Call<BaseBean<OrderDetailBean<MacSellPoBean>>> orderMangerReserveOrder2(@Query("theId") String theId);

    /**
     * 区域经理的订单详情-出租
     * @return .
     */
    @POST("order/detail")
    Call<BaseBean<OrderDetailBean<MacRentOutPoBean>>> orderMangerReserveOrder3(@Query("theId") String theId);

    /**
     * 区域经理的订单详情-承租
     * @return .
     */
    @POST("order/detail")
    Call<BaseBean<OrderDetailBean<MacRentIntPoBean>>> orderMangerReserveOrder4(@Query("theId") String theId);


    /**
     * 跟进订单
     * @return .
     */
    @POST("order/followOrder")
    Call<JsonEntity> orderFollowOrder(@Query("uId") int uId,@Query("osId") int osId);



    /**
     * 修改跟进订单状态
     * @return .
     */
    @POST("order/opOrder")
    Call<JsonEntity> orderOpOrder(@Query("uId") int uId, @Query("osId") String osId,
                                     @Query("remark")String remark,@Query("managerFlowNo") String managerFlowNo,
                                    @Query("managerConfirmPic")String managerConfirmPic,@Query("status") String status);





    /** ******************** 订单历史信息 **************************** */
    /**
     * 我的交易记录_买卖记录
     * @return .
     */
    @POST("order/userBuySellOrder")
    Call<BaseBean<PagesBean<MacOrderSubPo>>> orderUserBuySellOrder(@Query("uId") int uId, @Query("pageNo") int pageNo);
    /**
     * 我的交易记录_租赁记录
     * @return .
     */
    @POST("order/userRentOrder")
    Call<BaseBean<PagesBean<MacOrderSubPo>>> orderUserRentOrder(@Query("uId") int uId, @Query("pageNo") int pageNo);


    /**
     * 登录
     *
     * @param partList 表单信息
     * @return .
     */
    @GET("login")
    Call<ResponseBody> login();

    /**
     * 上传
     * Multipart 表示多表单上传,
     *
     * @param partList 表单信息
     * @return .
     */
    @Multipart
    @POST("uploadFile")
    Call<JsonEntity> uploadFile(@Part MultipartBody.Part fileData);


    /**
     * 上传多图上传
     * Multipart 表示多表单上传,
     *
     * @param partList 表单信息
     * @return .
     */
    @Multipart
    @POST("你的地址")
    Call<BaseBean> upLoadingAll(@Part List<MultipartBody.Part> partList);
}
