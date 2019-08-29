package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MachineTypeBean implements Parcelable {
        private int adminId;
        private String createDate;
        private String icon;
        private String mbId;
        private String name;
        private String status;


    public MachineTypeBean() {
    }

    public MachineTypeBean(int adminId, String createDate, String icon, String mbId, String name, String status) {
        this.adminId = adminId;
        this.createDate = createDate;
        this.icon = icon;
        this.mbId = mbId;
        this.name = name;
        this.status = status;
    }


    protected MachineTypeBean(Parcel in) {
        adminId = in.readInt();
        createDate = in.readString();
        icon = in.readString();
        mbId = in.readString();
        name = in.readString();
        status = in.readString();
    }

    public static final Creator<MachineTypeBean> CREATOR = new Creator<MachineTypeBean>() {
        @Override
        public MachineTypeBean createFromParcel(Parcel in) {
            return new MachineTypeBean(in);
        }

        @Override
        public MachineTypeBean[] newArray(int size) {
            return new MachineTypeBean[size];
        }
    };

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
        dest.writeInt(adminId);
        dest.writeString(createDate);
        dest.writeString(icon);
        dest.writeString(mbId);
        dest.writeString(name);
        dest.writeString(status);
    }
}
