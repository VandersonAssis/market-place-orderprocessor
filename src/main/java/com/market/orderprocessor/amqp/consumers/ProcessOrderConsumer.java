package com.market.orderprocessor.amqp.consumers;

import com.google.gson.Gson;
import com.market.orderprocessor.amqp.services.impl.AmqpServiceImpl;
import com.market.orderprocessor.models.ProductLock;
import com.market.orderprocessor.processors.ProductOrderProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessOrderConsumer {

    @Autowired
    private AmqpServiceImpl amqpService;

    @RabbitListener(queues = "${amqp.mppu.process.order.queue}")
    public void consume(String payload) throws Exception {
        //It's important to throw the exception above, or else the dead letter queue won't be filled when an exception happens here
        this.handleProcessing(payload);
    }

    private void handleProcessing(String payload) {
        ProductLock processedProduct = new ProductOrderProcessor().process(new Gson().fromJson(payload, ProductLock.class));
        this.amqpService.send(processedProduct);
    }

}
