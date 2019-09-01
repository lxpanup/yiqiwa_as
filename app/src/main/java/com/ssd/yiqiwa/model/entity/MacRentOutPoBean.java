package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class MacRentOutPoBean implements Parcelable {

         private String address;
          private String bargainPrice;
          private String boutique;
          private String capacity;
          private String city;
          private String companyName;
          private String contactPerson;
          private String contactPhone;
          private String county;
          private String coverImage;
          private String createDate;
          private String creater;
          private String describes;
          private String factoryDate;
          private String failReason;
          private String mbId;
          private String mbName;
          private String mbmId;
          private String mbmName;
          private String mtId;
          private String mtName;
          private String priceDay;
          private String priceHour;
          private String priceMonth;
          private String productDesc;
          private String province;
          private String rentFrom;
          private String roId;
          private String selfOperateGoods;
          private String selfProtectGoods;
          private String standard;
          private String status;
          private String theType;
          private String title;
          private String uId;
          private String userHeadUrl;
          private String userName;
          private String viewAmount;
          private String workTime;
          private String workTimeUint;
          private List<PictureBean> pictureList;

    public MacRentOutPoBean() {
    }

    public MacRentOutPoBean(String address, String bargainPrice, String boutique, String capacity, String city, String companyName, String contactPerson, String contactPhone, String county, String coverImage, String createDate, String creater, String describes, String factoryDate, String failReason, String mbId, String mbName, String mbmId, String mbmName, String mtId, String mtName, String priceDay, String priceHour, String priceMonth, String productDesc, String province, String rentFrom, String roId, String selfOperateGoods, String selfProtectGoods, String standard, String status, String theType, String title, String uId, String userHeadUrl, String userName, String viewAmount, String workTime, String workTimeUint, List<PictureBean> pictureList) {
        this.address = address;
        this.bargainPrice = bargainPrice;
        this.boutique = boutique;
        this.capacity = capacity;
        this.city = city;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.county = county;
        this.coverImage = coverImage;
        this.createDate = createDate;
        this.creater = creater;
        this.describes = describes;
        this.factoryDate = factoryDate;
        this.failReason = failReason;
        this.mbId = mbId;
        this.mbName = mbName;
        this.mbmId = mbmId;
        this.mbmName = mbmName;
        this.mtId = mtId;
        this.mtName = mtName;
        this.priceDay = priceDay;
        this.priceHour = priceHour;
        this.priceMonth = priceMonth;
        this.productDesc = productDesc;
        this.province = province;
        this.rentFrom = rentFrom;
        this.roId = roId;
        this.selfOperateGoods = selfOperateGoods;
        this.selfProtectGoods = selfProtectGoods;
        this.standard = standard;
        this.status = status;
        this.theType = theType;
        this.title = title;
        this.uId = uId;
        this.userHeadUrl = userHeadUrl;
        this.userName = userName;
        this.viewAmount = viewAmount;
        this.workTime = workTime;
        this.workTimeUint = workTimeUint;
        this.pictureList = pictureList;
    }


    protected MacRentOutPoBean(Parcel in) {
        address = in.readString();
        bargainPrice = in.readString();
        boutique = in.readString();
        capacity = in.readString();
        city = in.readString();
        companyName = in.readString();
        contactPerson = in.readString();
        contactPhone = in.readString();
        county = in.readString();
        coverImage = in.readString();
        createDate = in.readString();
        creater = in.readString();
        describes = in.readString();
        factoryDate = in.readString();
        failReason = in.readString();
        mbId = in.readString();
        mbName = in.readString();
        mbmId = in.readString();
        mbmName = in.readString();
        mtId = in.readString();
        mtName = in.readString();
        priceDay = in.readString();
        priceHour = in.readString();
        priceMonth = in.readString();
        productDesc = in.readString();
        province = in.readString();
        rentFrom = in.readString();
        roId = in.readString();
        selfOperateGoods = in.readString();
        selfProtectGoods = in.readString();
        standard = in.readString();
        status = in.readString();
        theType = in.readString();
        title = in.readString();
        uId = in.readString();
        userHeadUrl = in.readString();
        userName = in.readString();
        viewAmount = in.readString();
        workTime = in.readString();
        workTimeUint = in.readString();
        pictureList = in.createTypedArrayList(PictureBean.CREATOR);
    }

    public static final Creator<MacRentOutPoBean> CREATOR = new Creator<MacRentOutPoBean>() {
        @Override
        public MacRentOutPoBean createFromParcel(Parcel in) {
            return new MacRentOutPoBean(in);
        }

        @Override
        public MacRentOutPoBean[] newArray(int size) {
            return new MacRentOutPoBean[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBargainPrice() {
        return bargainPrice;
    }

    public void setBargainPrice(String bargainPrice) {
        this.bargainPrice = bargainPrice;
    }

    public String getBoutique() {
        return boutique;
    }

    public void setBoutique(String boutique) {
        this.boutique = boutique;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(String factoryDate) {
        this.factoryDate = factoryDate;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getMbName() {
        return mbName;
    }

    public void setMbName(String mbName) {
        this.mbName = mbName;
    }

    public String getMbmId() {
        return mbmId;
    }

    public void setMbmId(String mbmId) {
        this.mbmId = mbmId;
    }

    public String getMbmName() {
        return mbmName;
    }

    public void setMbmName(String mbmName) {
        this.mbmName = mbmName;
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId;
    }

    public String getMtName() {
        return mtName;
    }

    public void setMtName(String mtName) {
        this.mtName = mtName;
    }

    public String getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(String priceDay) {
        this.priceDay = priceDay;
    }

    public String getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(String priceHour) {
        this.priceHour = priceHour;
    }

    public String getPriceMonth() {
        return priceMonth;
    }

    public void setPriceMonth(String priceMonth) {
        this.priceMonth = priceMonth;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(String rentFrom) {
        this.rentFrom = rentFrom;
    }

    public String getRoId() {
        return roId;
    }

    public void setRoId(String roId) {
        this.roId = roId;
    }

    public String getSelfOperateGoods() {
        return selfOperateGoods;
    }

    public void setSelfOperateGoods(String selfOperateGoods) {
        this.selfOperateGoods = selfOperateGoods;
    }

    public String getSelfProtectGoods() {
        return selfProtectGoods;
    }

    public void setSelfProtectGoods(String selfProtectGoods) {
        this.selfProtectGoods = selfProtectGoods;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTheType() {
        return theType;
    }

    public void setTheType(String theType) {
        this.theType = theType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getViewAmount() {
        return viewAmount;
    }

    public void setViewAmount(String viewAmount) {
        this.viewAmount = viewAmount;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkTimeUint() {
        return workTimeUint;
    }

    public void setWorkTimeUint(String workTimeUint) {
        this.workTimeUint = workTimeUint;
    }

    public List<PictureBean> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<PictureBean> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(bargainPrice);
        dest.writeString(boutique);
        dest.writeString(capacity);
        dest.writeString(city);
        dest.writeString(companyName);
        dest.writeString(contactPerson);
        dest.writeString(contactPhone);
        dest.writeString(county);
        dest.writeString(coverImage);
        dest.writeString(createDate);
        dest.writeString(creater);
        dest.writeString(describes);
        dest.writeString(factoryDate);
        dest.writeString(failReason);
        dest.writeString(mbId);
        dest.writeString(mbName);
        dest.writeString(mbmId);
        dest.writeString(mbmName);
        dest.writeString(mtId);
        dest.writeString(mtName);
        dest.writeString(priceDay);
        dest.writeString(priceHour);
        dest.writeString(priceMonth);
        dest.writeString(productDesc);
        dest.writeString(province);
        dest.writeString(rentFrom);
        dest.writeString(roId);
        dest.writeString(selfOperateGoods);
        dest.writeString(selfProtectGoods);
        dest.writeString(standard);
        dest.writeString(status);
        dest.writeString(theType);
        dest.writeString(title);
        dest.writeString(uId);
        dest.writeString(userHeadUrl);
        dest.writeString(userName);
        dest.writeString(viewAmount);
        dest.writeString(workTime);
        dest.writeString(workTimeUint);
        dest.writeTypedList(pictureList);
    }
}
