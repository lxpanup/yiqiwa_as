package com.ssd.yiqiwa.model.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class JsonEntity implements Parcelable {

    private int code;
    /** 错误消息  */
    private String msg;
    /** 返回正确时候的数据 */
    private String data;

    public JsonEntity() {
    }

    public JsonEntity(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    protected JsonEntity(Parcel in) {
        code = in.readInt();
        msg = in.readString();
        data = in.readString();
    }

    public static final Creator<JsonEntity> CREATOR = new Creator<JsonEntity>() {
        @Override
        public JsonEntity createFromParcel(Parcel in) {
            return new JsonEntity(in);
        }

        @Override
        public JsonEntity[] newArray(int size) {
            return new JsonEntity[size];
        }
    };

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(msg);
        dest.writeString(data);
    }
}
