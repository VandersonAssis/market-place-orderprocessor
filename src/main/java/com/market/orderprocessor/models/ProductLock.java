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

    public ProductLock setLockId(String lockId) {
        this.lockId = lockId;
        return this;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public ProductLock setIdProduct(String idProduct) {
        this.idProduct = idProduct;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public ProductLock setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public ProductLock setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }
}
