package com.ssd.yiqiwa.model.entity;

public class UploadImageBean {

    private int id;
    private String pathFile;
    private String urlFile;

    public UploadImageBean() {
    }

    public UploadImageBean(int id, String pathFile, String urlFile) {
        this.id = id;
        this.pathFile = pathFile;
        this.urlFile = urlFile;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }


    public String getUrlFile() {
        return urlFile;
    }

    public void setUrlFile(String urlFile) {
        this.urlFile = urlFile;
    }
}
