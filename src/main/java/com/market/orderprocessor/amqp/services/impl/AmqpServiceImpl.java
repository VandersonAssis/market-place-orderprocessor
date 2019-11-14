package com.market.orderprocessor.amqp.services.impl;

import com.google.gson.Gson;
import com.market.orderprocessor.config.AmqpConfig;
import com.market.orderprocessor.models.ProductLock;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpServiceImpl {

    @Autowired
    private AmqpConfig amqpConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(ProductLock processedProduct) {
        this.rabbitTemplate.convertAndSend(this.amqpConfig.orderProcessedExchangeName(), "ORDER_PROCESSED", new Gson().toJson(processedProduct));
    }
}
