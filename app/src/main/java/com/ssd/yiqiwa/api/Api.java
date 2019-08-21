package com.ssd.yiqiwa.api;


import com.ssd.yiqiwa.model.entity.BaseBean;
import com.ssd.yiqiwa.model.entity.LoginUserBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
     * 下载文件
     * 如果下载大文件的一定要加上   @Streaming  注解
     *
     * @param fileUrl 文件的路径
     * @return 请求call
     */
    @GET("phone/15335514755/password/123456")
    Call<ResponseBody> download();
}
