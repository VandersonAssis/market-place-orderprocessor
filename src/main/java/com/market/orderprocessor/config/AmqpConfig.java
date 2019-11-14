package com.market.orderprocessor.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class AmqpConfig {
    @Value("${amqp.process.order.queue.name}")
    private String processOrderQueueName;

    @Value("${amqp.order.processed.queue.name}")
    private String orderProcessedQueueName;

    @Value("${amqp.order.processed.queue.name.ex}")
    private String orderProcessedQueueExchangeName;

    @Bean
    public Queue processOrderQueue() {
        return new Queue(this.processOrderQueueName);
    }

    @Bean
    public Declarables topicExchangeBindings() {
        Queue orderProcessedQueue = new Queue(this.orderProcessedQueueName);
        TopicExchange orderProcessedExchange = new TopicExchange(this.orderProcessedQueueExchangeName);

        return new Declarables(orderProcessedQueue, orderProcessedExchange, BindingBuilder.bind(orderProcessedQueue).to(orderProcessedExchange).with("ORDER_PROCESSED"));
    }

    public String orderProcessedExchangeName() {
        return this.orderProcessedQueueExchangeName;
    }
}
