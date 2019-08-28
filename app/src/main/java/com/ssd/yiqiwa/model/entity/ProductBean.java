package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductBean implements Parcelable {


    private String city;
    private String coverImage;
    private String createDate;
    private String factoryDate = "";
    private int id;
    private String price;
    private String priceUint;
    private String province;
    private String title;
    private int type;
    private int workHour;

    public ProductBean() {
    }

    public ProductBean(String city, String coverImage, String createDate, String factoryDate, int id, String price, String priceUint, String province, String title, int type, int workHour) {
        this.city = city;
        this.coverImage = coverImage;
        this.createDate = createDate;
        this.factoryDate = factoryDate;
        this.id = id;
        this.price = price;
        this.priceUint = priceUint;
        this.province = province;
        this.title = title;
        this.type = type;
        this.workHour = workHour;
    }

    protected ProductBean(Parcel in) {
        city = in.readString();
        coverImage = in.readString();
        createDate = in.readString();
        factoryDate = in.readString();
        id = in.readInt();
        price = in.readString();
        priceUint = in.readString();
        province = in.readString();
        title = in.readString();
        type = in.readInt();
        workHour = in.readInt();
    }

    public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel in) {
            return new ProductBean(in);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(String factoryDate) {
        this.factoryDate = factoryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceUint() {
        return priceUint;
    }

    public void setPriceUint(String priceUint) {
        this.priceUint = priceUint;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWorkHour() {
        return workHour;
    }

    public void setWorkHour(int workHour) {
        this.workHour = workHour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(coverImage);
        dest.writeString(createDate);
        dest.writeString(factoryDate);
        dest.writeInt(id);
        dest.writeString(price);
        dest.writeString(priceUint);
        dest.writeString(province);
        dest.writeString(title);
        dest.writeInt(type);
        dest.writeInt(workHour);
    }
}
