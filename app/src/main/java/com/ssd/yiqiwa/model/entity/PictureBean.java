package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PictureBean implements Parcelable {
    private String createDate;
    private String roId;
    private String ropId;
    private String url;

    public PictureBean() {
    }


    public PictureBean(String createDate, String roId, String ropId, String url) {
        this.createDate = createDate;
        this.roId = roId;
        this.ropId = ropId;
        this.url = url;
    }


    protected PictureBean(Parcel in) {
        createDate = in.readString();
        roId = in.readString();
        ropId = in.readString();
        url = in.readString();
    }

    public static final Creator<PictureBean> CREATOR = new Creator<PictureBean>() {
        @Override
        public PictureBean createFromParcel(Parcel in) {
            return new PictureBean(in);
        }

        @Override
        public PictureBean[] newArray(int size) {
            return new PictureBean[size];
        }
    };

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRoId() {
        return roId;
    }

    public void setRoId(String roId) {
        this.roId = roId;
    }

    public String getRopId() {
        return ropId;
    }

    public void setRopId(String ropId) {
        this.ropId = ropId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createDate);
        dest.writeString(roId);
        dest.writeString(ropId);
        dest.writeString(url);
    }
}
