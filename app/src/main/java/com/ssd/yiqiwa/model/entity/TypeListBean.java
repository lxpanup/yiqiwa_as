package com.ssd.yiqiwa.model.entity;

public class TypeListBean {
    
  private String createDate;
          private String mtId;
          private String name;
          private String riId;
          private String ritId;

    public TypeListBean() {
    }

    public TypeListBean(String createDate, String mtId, String name, String riId, String ritId) {
        this.createDate = createDate;
        this.mtId = mtId;
        this.name = name;
        this.riId = riId;
        this.ritId = ritId;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMtId() {
        return mtId;
    }

    public void setMtId(String mtId) {
        this.mtId = mtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiId() {
        return riId;
    }

    public void setRiId(String riId) {
        this.riId = riId;
    }

    public String getRitId() {
        return ritId;
    }

    public void setRitId(String ritId) {
        this.ritId = ritId;
    }
}
