package com.ssd.yiqiwa.api;


import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.JsonEntity;
import com.ssd.yiqiwa.model.entity.LoginUserBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
     * 注册
     * @return .
     */
    @POST("userDetail")
    Call<BaseBean<LoginUserBean>> userDetail(@Query("uId") int uId);




    /**
     * 首页banner
     * @return .
     */
    @POST("home/banner")
    Call<BaseBean<LoginUserBean>> homeBanner();

    /**
     * 推荐产品-新品推荐
     * @return .
     */
    @POST("home/newProduct")
    Call<BaseBean<LoginUserBean>> homeNewProduct();

    /**
     * 特价专区
     * @return .
     */
    @POST("home/discountZone")
    Call<BaseBean<LoginUserBean>> homeDiscountZone();







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
    Call<JsonEntity> uploadFile(@Part MultipartBody.Part partList);


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
