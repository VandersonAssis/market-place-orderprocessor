package com.market.orderprocessor.amqp.consumers;

import com.google.gson.Gson;
import com.market.orderprocessor.amqp.services.AmqpService;
import com.market.orderprocessor.models.ProductLock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.market.orderprocessor.TestDataBuilder.buildProductLock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ProcessOrderConsumerTest {
    private ProcessOrderConsumer processOrderConsumer;

    @Mock
    private AmqpService amqpService;

    @Before
    public void setUp() {
        this.processOrderConsumer = new ProcessOrderConsumer();
        ReflectionTestUtils.setField(this.processOrderConsumer, "amqpService", this.amqpService);
    }

    @Test
    public void shouldConsumePayloadAndSendToProcessedQueue() throws Exception {
        doNothing().when(this.amqpService).send(any(ProductLock.class));
        this.processOrderConsumer.consume(new Gson().toJson(buildProductLock()));

        verify(this.amqpService, times(1)).send(any(ProductLock.class));
    }
}