package com.ssd.yiqiwa.api;


import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.BaseBeanList;
import com.ssd.yiqiwa.model.entity.HomeBannerImages;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.LoginUserBean;
import com.ssd.yiqiwa.model.entity.MachineBrandBean;
import com.ssd.yiqiwa.model.entity.MachineModelBean;
import com.ssd.yiqiwa.model.entity.MachineTypeBean;
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

    /**
     * 特价专区
     * @return .
     */
    @POST("rentOut/add")
    Call<BaseBean<JsonEntity>> rentOutAdd(@QueryMap Map<String, Object> map);






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
