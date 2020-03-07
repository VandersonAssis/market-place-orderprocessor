package com.market.orderprocessor;

import com.market.orderprocessor.models.ProductLock;

public abstract class TestDataBuilder {
    public static ProductLock buildProductLock() {
        return new ProductLock()
                .setLockId("lock_test_id")
                .setIdProduct("test_id_product")
                .setQuantity(10)
                .setOrderStatus(ProductLock.OrderStatus.PENDING);
    }
}
