package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MachineModelBean implements Parcelable {
        
        
   private String adminId;
           private String createDate;
           private String mbId;
           private String mbmId;
           private String name;
           private String status;
           private String tonnage;
           private String type;

    public MachineModelBean() {
    }

    public MachineModelBean(String adminId, String createDate, String mbId, String mbmId, String name, String status, String tonnage, String type) {
        this.adminId = adminId;
        this.createDate = createDate;
        this.mbId = mbId;
        this.mbmId = mbmId;
        this.name = name;
        this.status = status;
        this.tonnage = tonnage;
        this.type = type;
    }


    protected MachineModelBean(Parcel in) {
        adminId = in.readString();
        createDate = in.readString();
        mbId = in.readString();
        mbmId = in.readString();
        name = in.readString();
        status = in.readString();
        tonnage = in.readString();
        type = in.readString();
    }

    public static final Creator<MachineModelBean> CREATOR = new Creator<MachineModelBean>() {
        @Override
        public MachineModelBean createFromParcel(Parcel in) {
            return new MachineModelBean(in);
        }

        @Override
        public MachineModelBean[] newArray(int size) {
            return new MachineModelBean[size];
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

    public String getMbmId() {
        return mbmId;
    }

    public void setMbmId(String mbmId) {
        this.mbmId = mbmId;
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

    public String getTonnage() {
        return tonnage;
    }

    public void setTonnage(String tonnage) {
        this.tonnage = tonnage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        dest.writeString(mbmId);
        dest.writeString(name);
        dest.writeString(status);
        dest.writeString(tonnage);
        dest.writeString(type);
    }
}
