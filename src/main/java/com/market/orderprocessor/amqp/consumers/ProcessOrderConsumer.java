package com.market.orderprocessor.amqp.consumers;

import com.google.gson.Gson;
import com.market.orderprocessor.amqp.services.AmqpService;
import com.market.orderprocessor.models.ProductLock;
import com.market.orderprocessor.processors.ProductOrderProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessOrderConsumer {
    private static final Logger log = LogManager.getLogger(ProcessOrderConsumer.class);

    @Autowired
    private AmqpService amqpService;

    @RabbitListener(queues = "${amqp.mppu.process.order.queue}")
    public void consume(String payload) throws Exception {
        //It's important to throw the exception above, or else the dead letter queue won't be filled when an exception happens here

        log.info("Begin consuming process order queue");
        ProductLock processedProduct = new ProductOrderProcessor().process(new Gson().fromJson(payload, ProductLock.class));
        log.info("Processed {} product with quantity of {}", processedProduct.getIdProduct(), processedProduct.getQuantity());
        this.amqpService.send(processedProduct);
    }
}
