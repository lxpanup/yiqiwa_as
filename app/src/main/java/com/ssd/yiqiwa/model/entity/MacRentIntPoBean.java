package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class MacRentIntPoBean implements Parcelable {

      private String address;
              private String arrivalTime;
              private String city;
              private String contactPerson;
              private String contactPhone;
              private String count;
              private String county;
              private String coverImage;
              private String createDate;
              private String creater;
              private String describes;
              private String factoryDate;
              private String failReason;
              private String paymentMethod;
              private String priceDay;
              private String priceHour;
              private String priceMonth;
              private String priceUint;
              private String projectType;
              private String province;
              private String rentFrom;
              private String riId;
              private String standard;
              private String status;
              private String title;
              private String tonnage;
             private String uId;
             private String viewAmount;
             private String workTime;
             private String workTimeUint;
            private List<TypeListBean> typeList;

    public MacRentIntPoBean() {
    }

    public MacRentIntPoBean(String address, String arrivalTime, String city, String contactPerson, String contactPhone, String count, String county, String coverImage, String createDate, String creater, String describes, String factoryDate, String failReason, String paymentMethod, String priceDay, String priceHour, String priceMonth, String priceUint, String projectType, String province, String rentFrom, String riId, String standard, String status, String title, String tonnage, String uId, String viewAmount, String workTime, String workTimeUint, List<TypeListBean> typeList) {
        this.address = address;
        this.arrivalTime = arrivalTime;
        this.city = city;
        this.contactPerson = contactPerson;
        this.contactPhone = contactPhone;
        this.count = count;
        this.county = county;
        this.coverImage = coverImage;
        this.createDate = createDate;
        this.creater = creater;
        this.describes = describes;
        this.factoryDate = factoryDate;
        this.failReason = failReason;
        this.paymentMethod = paymentMethod;
        this.priceDay = priceDay;
        this.priceHour = priceHour;
        this.priceMonth = priceMonth;
        this.priceUint = priceUint;
        this.projectType = projectType;
        this.province = province;
        this.rentFrom = rentFrom;
        this.riId = riId;
        this.standard = standard;
        this.status = status;
        this.title = title;
        this.tonnage = tonnage;
        this.uId = uId;
        this.viewAmount = viewAmount;
        this.workTime = workTime;
        this.workTimeUint = workTimeUint;
        this.typeList = typeList;
    }


    protected MacRentIntPoBean(Parcel in) {
        address = in.readString();
        arrivalTime = in.readString();
        city = in.readString();
        contactPerson = in.readString();
        contactPhone = in.readString();
        count = in.readString();
        county = in.readString();
        coverImage = in.readString();
        createDate = in.readString();
        creater = in.readString();
        describes = in.readString();
        factoryDate = in.readString();
        failReason = in.readString();
        paymentMethod = in.readString();
        priceDay = in.readString();
        priceHour = in.readString();
        priceMonth = in.readString();
        priceUint = in.readString();
        projectType = in.readString();
        province = in.readString();
        rentFrom = in.readString();
        riId = in.readString();
        standard = in.readString();
        status = in.readString();
        title = in.readString();
        tonnage = in.readString();
        uId = in.readString();
        viewAmount = in.readString();
        workTime = in.readString();
        workTimeUint = in.readString();
    }

    public static final Creator<MacRentIntPoBean> CREATOR = new Creator<MacRentIntPoBean>() {
        @Override
        public MacRentIntPoBean createFromParcel(Parcel in) {
            return new MacRentIntPoBean(in);
        }

        @Override
        public MacRentIntPoBean[] newArray(int size) {
            return new MacRentIntPoBean[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public String getPriceUint() {
        return priceUint;
    }

    public void setPriceUint(String priceUint) {
        this.priceUint = priceUint;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
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

    public String getRiId() {
        return riId;
    }

    public void setRiId(String riId) {
        this.riId = riId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(address);
        dest.writeString(arrivalTime);
        dest.writeString(city);
        dest.writeString(contactPerson);
        dest.writeString(contactPhone);
        dest.writeString(count);
        dest.writeString(county);
        dest.writeString(coverImage);
        dest.writeString(createDate);
        dest.writeString(creater);
        dest.writeString(describes);
        dest.writeString(factoryDate);
        dest.writeString(failReason);
        dest.writeString(paymentMethod);
        dest.writeString(priceDay);
        dest.writeString(priceHour);
        dest.writeString(priceMonth);
        dest.writeString(priceUint);
        dest.writeString(projectType);
        dest.writeString(province);
        dest.writeString(rentFrom);
        dest.writeString(riId);
        dest.writeString(standard);
        dest.writeString(status);
        dest.writeString(title);
        dest.writeString(tonnage);
        dest.writeString(uId);
        dest.writeString(viewAmount);
        dest.writeString(workTime);
        dest.writeString(workTimeUint);
    }
}
