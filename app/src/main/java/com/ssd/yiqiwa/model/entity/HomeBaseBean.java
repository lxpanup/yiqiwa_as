package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;


public class HomeBaseBean implements Parcelable, MultiItemEntity {
    public static final String NULL_STRING = "";
    public static final double ZERO = 0;
    public static final int TYPE_PLACE = -1;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_CAROUSEL = 3;
    public static final int TYPE_CATEGORY = 4;
    public static final int TYPE_HEADLINE = 5;
    public static final int TYPE_DIVIDER = 6;
    public static final int TYPE_LIVE = 7;
    public static final int TYPE_HOT = 9;
    public static final int TYPE_RECOMMEND = 12;
    private int itemType;
    private int spanSize;



    private String product_city;
    private String product_coverImage;
    private String product_createDate;
    private String product_factoryDate;
    private String product_id;
    private String product_price;
    private String product_priceUint;
    private String product_province;
    private String product_title;
    private String product_type;
    private String product_workHour;



    private int type = TYPE_RECOMMEND;
    private int spanCount = 300;


    public HomeBaseBean(String product_city, String product_coverImage, String product_createDate, String product_factoryDate, String product_id, String product_price, String product_priceUint, String product_province, String product_title, String product_type, String product_workHour, int type, int spanCount) {
        this.product_city = product_city;
        this.product_coverImage = product_coverImage;
        this.product_createDate = product_createDate;
        this.product_factoryDate = product_factoryDate;
        this.product_id = product_id;
        this.product_price = product_price;
        this.product_priceUint = product_priceUint;
        this.product_province = product_province;
        this.product_title = product_title;
        this.product_type = product_type;
        this.product_workHour = product_workHour;
        this.type = type;
        this.spanCount = spanCount;
    }

    protected HomeBaseBean(Parcel in) {
        product_city = in.readString();
        product_coverImage = in.readString();
        product_createDate = in.readString();
        product_factoryDate = in.readString();
        product_id = in.readString();
        product_price = in.readString();
        product_priceUint = in.readString();
        product_province = in.readString();
        product_title = in.readString();
        product_type = in.readString();
        product_workHour = in.readString();
        type = in.readInt();
        spanCount = in.readInt();
    }

    public static final Creator<HomeBaseBean> CREATOR = new Creator<HomeBaseBean>() {
        @Override
        public HomeBaseBean createFromParcel(Parcel in) {
            return new HomeBaseBean(in);
        }

        @Override
        public HomeBaseBean[] newArray(int size) {
            return new HomeBaseBean[size];
        }
    };

    public String getProduct_city() {
        return product_city;
    }

    public void setProduct_city(String product_city) {
        this.product_city = product_city;
    }

    public String getProduct_coverImage() {
        return product_coverImage;
    }

    public void setProduct_coverImage(String product_coverImage) {
        this.product_coverImage = product_coverImage;
    }

    public String getProduct_createDate() {
        return product_createDate;
    }

    public void setProduct_createDate(String product_createDate) {
        this.product_createDate = product_createDate;
    }

    public String getProduct_factoryDate() {
        return product_factoryDate;
    }

    public void setProduct_factoryDate(String product_factoryDate) {
        this.product_factoryDate = product_factoryDate;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_priceUint() {
        return product_priceUint;
    }

    public void setProduct_priceUint(String product_priceUint) {
        this.product_priceUint = product_priceUint;
    }

    public String getProduct_province() {
        return product_province;
    }

    public void setProduct_province(String product_province) {
        this.product_province = product_province;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getProduct_workHour() {
        return product_workHour;
    }

    public void setProduct_workHour(String product_workHour) {
        this.product_workHour = product_workHour;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_city);
        dest.writeString(product_coverImage);
        dest.writeString(product_createDate);
        dest.writeString(product_factoryDate);
        dest.writeString(product_id);
        dest.writeString(product_price);
        dest.writeString(product_priceUint);
        dest.writeString(product_province);
        dest.writeString(product_title);
        dest.writeString(product_type);
        dest.writeString(product_workHour);
        dest.writeInt(type);
        dest.writeInt(spanCount);
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
