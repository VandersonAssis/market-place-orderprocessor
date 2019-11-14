package com.market.orderprocessor.models;

public class ProductLock {
    private String lockId;
    private String idProduct;
    private Integer quantity;
    private OrderStatus orderStatus;

    public enum OrderStatus {
        PENDING,
        PROCESSING,
        PAYMENT_NOT_AUTHORIZED,
        FINISHED
    }

    //getters and setters
    public String getLockId() {
        return lockId;
    }

    public void setLockId(String lockId) {
        this.lockId = lockId;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
