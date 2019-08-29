package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MachineBrandBean implements Parcelable {
        private String adminId;
        private String createDate;
        private String mbId;
        private String name;
        private String status;


    public MachineBrandBean() {
    }



    public MachineBrandBean(String adminId, String createDate, String mbId, String name, String status) {
        this.adminId = adminId;
        this.createDate = createDate;
        this.mbId = mbId;
        this.name = name;
        this.status = status;
    }


    protected MachineBrandBean(Parcel in) {
        adminId = in.readString();
        createDate = in.readString();
        mbId = in.readString();
        name = in.readString();
        status = in.readString();
    }

    public static final Creator<MachineBrandBean> CREATOR = new Creator<MachineBrandBean>() {
        @Override
        public MachineBrandBean createFromParcel(Parcel in) {
            return new MachineBrandBean(in);
        }

        @Override
        public MachineBrandBean[] newArray(int size) {
            return new MachineBrandBean[size];
        }
    };

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adminId);
        dest.writeString(createDate);
        dest.writeString(mbId);
        dest.writeString(name);
        dest.writeString(status);
    }
}
