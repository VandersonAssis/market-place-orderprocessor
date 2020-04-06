package com.market.orderprocessor.processors;

import com.market.orderprocessor.models.ProductLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class ProductOrderProcessor {
    private static final Logger log = LogManager.getLogger(ProductOrderProcessor.class);

    public ProductLock process(ProductLock productLock) {
        //This is just a mock processing, it does nothing but wait a little and update orderStatus to FINISHED
        try {
            log.info("Begin processing {} lockId with quantity of {} and orderStatus {}", productLock.getLockId(), productLock.getQuantity(),
                    productLock.getOrderStatus());

            productLock.setOrderStatus(ProductLock.OrderStatus.FINISHED);
            TimeUnit.SECONDS.sleep(5);
        } catch(InterruptedException ex) {}

        log.info("Finished processing {} lockId with quantity of {} and orderStatus {}", productLock.getLockId(), productLock.getQuantity(),
                productLock.getOrderStatus());

        return productLock;
    }
}
