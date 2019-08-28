package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeBannerImages implements Parcelable {
        private String createDate;
        private int hbId;
        private String imageUrl;
        private String jump;
        private int sortNo;
        private int status;
        private int syId;
        private String title;
        private int type;


    protected HomeBannerImages(Parcel in) {
        createDate = in.readString();
        hbId = in.readInt();
        imageUrl = in.readString();
        jump = in.readString();
        sortNo = in.readInt();
        status = in.readInt();
        syId = in.readInt();
        title = in.readString();
        type = in.readInt();
    }

    public static final Creator<HomeBannerImages> CREATOR = new Creator<HomeBannerImages>() {
        @Override
        public HomeBannerImages createFromParcel(Parcel in) {
            return new HomeBannerImages(in);
        }

        @Override
        public HomeBannerImages[] newArray(int size) {
            return new HomeBannerImages[size];
        }
    };

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getHbId() {
        return hbId;
    }

    public void setHbId(int hbId) {
        this.hbId = hbId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJump() {
        return jump;
    }

    public void setJump(String jump) {
        this.jump = jump;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSyId() {
        return syId;
    }

    public void setSyId(int syId) {
        this.syId = syId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createDate);
        dest.writeInt(hbId);
        dest.writeString(imageUrl);
        dest.writeString(jump);
        dest.writeInt(sortNo);
        dest.writeInt(status);
        dest.writeInt(syId);
        dest.writeString(title);
        dest.writeInt(type);
    }
}
