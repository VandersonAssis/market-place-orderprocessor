package com.market.orderprocessor.processors;

import com.market.orderprocessor.models.ProductLock;

import java.util.concurrent.TimeUnit;

public class ProductOrderProcessor {

    public ProductLock process(ProductLock productLock) {
        return this.simulateOrderProcessing(productLock);
    }

    private ProductLock simulateOrderProcessing(ProductLock productLock) {
        try {
            productLock.setOrderStatus(ProductLock.OrderStatus.FINISHED);
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException ex) {}

        return productLock;
    }
}
