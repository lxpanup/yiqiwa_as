package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MacOrderSubPo implements Parcelable {


  private String city;
          private String count;
          private String county;
          private String createDate;
          private String giftScore;
          private String giftScoreRemark;
          private String managerId;
          private String managerStart;
          private String orderNum;
          private String orderSubNum;
          private String orderType;
          private String osId;
          private String price;
          private String priceUint;
          private String productCoverImage;
          private String productId;
          private String productTitle;
          private String province;
          private String publisherId;
          private String remark;
          private String rentFrom;
          private String status;
          private String userId;

    public MacOrderSubPo() {
    }


    public MacOrderSubPo(String city, String count, String county, String createDate, String giftScore, String giftScoreRemark, String managerId, String managerStart, String orderNum, String orderSubNum, String orderType, String osId, String price, String priceUint, String productCoverImage, String productId, String productTitle, String province, String publisherId, String remark, String rentFrom, String status, String userId) {
        this.city = city;
        this.count = count;
        this.county = county;
        this.createDate = createDate;
        this.giftScore = giftScore;
        this.giftScoreRemark = giftScoreRemark;
        this.managerId = managerId;
        this.managerStart = managerStart;
        this.orderNum = orderNum;
        this.orderSubNum = orderSubNum;
        this.orderType = orderType;
        this.osId = osId;
        this.price = price;
        this.priceUint = priceUint;
        this.productCoverImage = productCoverImage;
        this.productId = productId;
        this.productTitle = productTitle;
        this.province = province;
        this.publisherId = publisherId;
        this.remark = remark;
        this.rentFrom = rentFrom;
        this.status = status;
        this.userId = userId;
    }


    protected MacOrderSubPo(Parcel in) {
        city = in.readString();
        count = in.readString();
        county = in.readString();
        createDate = in.readString();
        giftScore = in.readString();
        giftScoreRemark = in.readString();
        managerId = in.readString();
        managerStart = in.readString();
        orderNum = in.readString();
        orderSubNum = in.readString();
        orderType = in.readString();
        osId = in.readString();
        price = in.readString();
        priceUint = in.readString();
        productCoverImage = in.readString();
        productId = in.readString();
        productTitle = in.readString();
        province = in.readString();
        publisherId = in.readString();
        remark = in.readString();
        rentFrom = in.readString();
        status = in.readString();
        userId = in.readString();
    }

    public static final Creator<MacOrderSubPo> CREATOR = new Creator<MacOrderSubPo>() {
        @Override
        public MacOrderSubPo createFromParcel(Parcel in) {
            return new MacOrderSubPo(in);
        }

        @Override
        public MacOrderSubPo[] newArray(int size) {
            return new MacOrderSubPo[size];
        }
    };

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGiftScore() {
        return giftScore;
    }

    public void setGiftScore(String giftScore) {
        this.giftScore = giftScore;
    }

    public String getGiftScoreRemark() {
        return giftScoreRemark;
    }

    public void setGiftScoreRemark(String giftScoreRemark) {
        this.giftScoreRemark = giftScoreRemark;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerStart() {
        return managerStart;
    }

    public void setManagerStart(String managerStart) {
        this.managerStart = managerStart;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderSubNum() {
        return orderSubNum;
    }

    public void setOrderSubNum(String orderSubNum) {
        this.orderSubNum = orderSubNum;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
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

    public String getProductCoverImage() {
        return productCoverImage;
    }

    public void setProductCoverImage(String productCoverImage) {
        this.productCoverImage = productCoverImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(String rentFrom) {
        this.rentFrom = rentFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(count);
        dest.writeString(county);
        dest.writeString(createDate);
        dest.writeString(giftScore);
        dest.writeString(giftScoreRemark);
        dest.writeString(managerId);
        dest.writeString(managerStart);
        dest.writeString(orderNum);
        dest.writeString(orderSubNum);
        dest.writeString(orderType);
        dest.writeString(osId);
        dest.writeString(price);
        dest.writeString(priceUint);
        dest.writeString(productCoverImage);
        dest.writeString(productId);
        dest.writeString(productTitle);
        dest.writeString(province);
        dest.writeString(publisherId);
        dest.writeString(remark);
        dest.writeString(rentFrom);
        dest.writeString(status);
        dest.writeString(userId);
    }
}
