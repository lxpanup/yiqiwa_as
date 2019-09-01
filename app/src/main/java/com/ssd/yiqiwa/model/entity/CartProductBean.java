package com.ssd.yiqiwa.model.entity;

public class CartProductBean {

    private String productId;
    private String productType;
    private String rentFrom;
    private String coverImage;
    private String productTile;
    private String projectType;
    private String productPrice;
    private String productPriceUint;

    public CartProductBean() {
    }

    public CartProductBean(String productId, String productType, String rentFrom, String coverImage, String productTile, String projectType, String productPrice, String productPriceUint) {
        this.productId = productId;
        this.productType = productType;
        this.rentFrom = rentFrom;
        this.coverImage = coverImage;
        this.productTile = productTile;
        this.projectType = projectType;
        this.productPrice = productPrice;
        this.productPriceUint = productPriceUint;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRentFrom() {
        return rentFrom;
    }

    public void setRentFrom(String rentFrom) {
        this.rentFrom = rentFrom;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getProductTile() {
        return productTile;
    }

    public void setProductTile(String productTile) {
        this.productTile = productTile;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPriceUint() {
        return productPriceUint;
    }

    public void setProductPriceUint(String productPriceUint) {
        this.productPriceUint = productPriceUint;
    }
}
