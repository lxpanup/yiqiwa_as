package com.ssd.yiqiwa.model.entity;

public class CartProductBean {

    private String productId;
    /** 1.购买 2.出售 3.承租  4.出售 */
    private String productType;
    private String rentFrom;
    private String coverImage;
    private String productTile;
    private String projectType;
    private String productPrice;
    private String priceDay;
    private String priceHour;
    private String priceMonth;
    private String productPriceUint;
    private String productCount;
    private String productRemarks;
    private boolean isCartCheckbox;

    public CartProductBean() {
    }

    public CartProductBean(String productId, String productType, String rentFrom, String coverImage, String productTile, String projectType, String productPrice, String priceDay, String priceHour, String priceMonth, String productPriceUint, String productCount, String productRemarks, boolean isCartCheckbox) {
        this.productId = productId;
        this.productType = productType;
        this.rentFrom = rentFrom;
        this.coverImage = coverImage;
        this.productTile = productTile;
        this.projectType = projectType;
        this.productPrice = productPrice;
        this.priceDay = priceDay;
        this.priceHour = priceHour;
        this.priceMonth = priceMonth;
        this.productPriceUint = productPriceUint;
        this.productCount = productCount;
        this.productRemarks = productRemarks;
        this.isCartCheckbox = isCartCheckbox;
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

    public String getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(String priceDay) {
        this.priceDay = priceDay;
    }

    public String getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(String priceHour) {
        this.priceHour = priceHour;
    }

    public String getPriceMonth() {
        return priceMonth;
    }

    public void setPriceMonth(String priceMonth) {
        this.priceMonth = priceMonth;
    }

    public String getProductPriceUint() {
        return productPriceUint;
    }

    public void setProductPriceUint(String productPriceUint) {
        this.productPriceUint = productPriceUint;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getProductRemarks() {
        return productRemarks;
    }

    public void setProductRemarks(String productRemarks) {
        this.productRemarks = productRemarks;
    }

    public boolean isCartCheckbox() {
        return isCartCheckbox;
    }

    public void setCartCheckbox(boolean cartCheckbox) {
        isCartCheckbox = cartCheckbox;
    }
}
