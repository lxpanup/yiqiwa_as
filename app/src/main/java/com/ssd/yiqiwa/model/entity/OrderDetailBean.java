package com.ssd.yiqiwa.model.entity;



public class OrderDetailBean<T> {


    private T product;
    private MacOrderSubPo order;
    private LoginUserBean user;

    public OrderDetailBean() {
    }

    public OrderDetailBean(T product, MacOrderSubPo order, LoginUserBean user) {
        this.product = product;
        this.order = order;
        this.user = user;
    }


    public T getProduct() {
        return product;
    }

    public void setProduct(T product) {
        this.product = product;
    }

    public MacOrderSubPo getOrder() {
        return order;
    }

    public void setOrder(MacOrderSubPo order) {
        this.order = order;
    }

    public LoginUserBean getUser() {
        return user;
    }

    public void setUser(LoginUserBean user) {
        this.user = user;
    }
}
