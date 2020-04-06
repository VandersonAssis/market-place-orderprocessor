package com.market.orderprocessor.amqp.services;

import com.market.orderprocessor.models.ProductLock;

public interface AmqpService {
    void send(ProductLock productLock);
}
