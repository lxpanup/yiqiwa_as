package com.ssd.yiqiwa.utils;

import com.zaaach.citypicker.model.HotCity;

import java.util.ArrayList;
import java.util.List;

public class Constants {

    public static String BASE_URL = "http://192.168.0.178:7000/mobile/api/";


    public static final String LOGIN_VERSION_INDEX = "login_version_index";


    public static final String LOGIN_USER_PATH = BASE_URL + "/login";

    /**
     * 缓存文件保存路径
     */
    public static final String CACHE_FILE_PATH = "/smile/cache/";



    /** 用户的ID */
    public static final String SP_USER_ID = "yqw_user_id";
    /** 用户昵称*/
    public static final String SP_USER_NICKNAME  = "user_nick_name";
    /** 登录的电话*/
    public static final String SP_USER_LOGINPHONE  = "user_login_phone";
    /** 登录的密码*/
    public static final String SP_USER_PASSWORD  = "user_password";
    /** 头像的地址*/
    public static final String SP_USER_PORTRAIT  = "user_portrait";
    /** 用户类型 1是普通用户 2是经理*/
    public static final String SP_USER_TYPE  = "user_type";
    /** 生日*/
    public static final String SP_USER_BIRTHDAY  = "user_birthday";
    /** 所有的积分*/
    public static final String SP_USER_TOTALSCORE  = "user_totalScore";
    /** 可以提现的积分*/
    public static final String SP_USER_LEFTSCORE  = "user_leftScore";
    /** 我的邀请码*/
    public static final String SP_USER_MYCODE  = "user_myCode";
    /** 银行卡号*/
    public static final String SP_USER_CARDNUMBER  = "user_cardNumber";
    /** 银行*/
    public static final String SP_USER_CARDBANK  = "user_cardBank";
    /** 账号状态 0正常 1拉黑 不能登录系统*/
    public static final String SP_USER_STATUS  = "user_status";
    /** 联系电话*/
    public static final String SP_USER_CONTACTPHONE  = "user_contact_phone";





    /** 相应成功标识 */
    public static int HTTP_RESPONSE_OK = 200;
    /** 相应失败标识 */
    public static int HTTP_RESPONSE_ERROR = 10000;


    /**
     * 热门地址
     * @return
     */
    public static List<HotCity> getHotCitys(){
        List<HotCity> hotCities = new ArrayList<>();
        hotCities.add(new HotCity("成都", "四川", "101270101"));
        hotCities.add(new HotCity("深圳", "广东", "101280601"));
        return hotCities;
    }


}