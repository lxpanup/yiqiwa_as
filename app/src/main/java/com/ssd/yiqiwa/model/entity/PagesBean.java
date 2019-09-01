package com.ssd.yiqiwa.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class PagesBean<T> implements Parcelable {


   private String current;
   private String pages;
   private List<T> records;
   private String searchCount;
   private String size;
   private String total;

    public PagesBean() {
    }


    public PagesBean(String current, String pages, List<T> records, String searchCount, String size, String total) {
        this.current = current;
        this.pages = pages;
        this.records = records;
        this.searchCount = searchCount;
        this.size = size;
        this.total = total;
    }


    protected PagesBean(Parcel in) {
        current = in.readString();
        pages = in.readString();
        searchCount = in.readString();
        size = in.readString();
        total = in.readString();
    }

    public static final Creator<PagesBean> CREATOR = new Creator<PagesBean>() {
        @Override
        public PagesBean createFromParcel(Parcel in) {
            return new PagesBean(in);
        }

        @Override
        public PagesBean[] newArray(int size) {
            return new PagesBean[size];
        }
    };

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public String getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(String searchCount) {
        this.searchCount = searchCount;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(current);
        dest.writeString(pages);
        dest.writeString(searchCount);
        dest.writeString(size);
        dest.writeString(total);
    }
}
