package com.market.orderprocessor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@EnableRabbit
@Configuration
public class AmqpConfig {
    @Value("${amqp.mpop.order.processed.queue}")
    private String orderProcessedQueueName;

    @Value("${amqp.mpop.order.processed.ex}")
    private String orderProcessedExchangeName;

    @Value("${amqp.mpop.order.processed.dlq}")
    private String orderProcessedDlqName;

    @Value("${amqp.mpop.order.processed.dlx}")
    private String orderProcessedDlxName;

    @Value("${amqp.mpop.order.processed.routing.key}")
    private String orderProcessedRoutingKey;

    @Bean
    public Queue orderProcessedQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", this.orderProcessedDlxName);
        args.put("x-dead-letter-routing-key", this.orderProcessedRoutingKey);
        return new Queue(this.orderProcessedQueueName, true, false, false, args);
    }

    @Bean
    public Declarables orderProcessedBindings() {
        Queue orderProcessedQueue = this.orderProcessedQueue();
        TopicExchange orderProcessedExchange = new TopicExchange(this.orderProcessedExchangeName);

        return new Declarables(orderProcessedQueue, orderProcessedExchange, BindingBuilder.bind(orderProcessedQueue)
                .to(orderProcessedExchange).with(this.orderProcessedRoutingKey));
    }

    @Bean
    public Queue orderProcessedDlq() {
         return new Queue(this.orderProcessedDlqName);
    }

    @Bean
    public TopicExchange orderProcessedDlx() {
        return new TopicExchange(this.orderProcessedDlxName);
    }

    @Bean
    public Binding bindOrderProcessedDlq() {
        return BindingBuilder.bind(this.orderProcessedDlq()).to(this.orderProcessedDlx())
                .with(this.orderProcessedRoutingKey);
    }

    public String orderProcessedExchangeName() {
        return this.orderProcessedExchangeName;
    }
}
