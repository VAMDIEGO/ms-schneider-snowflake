package com.ms.schneider.service.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import com.ms.schneider.dto.CustomerDTO;

@ExtendWith(MockitoExtension.class)
class CustomerKafkaProducerTest {

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private CustomerKafkaProducer customerKafkaProducer;

    private CustomerDTO sampleCustomer;

    @BeforeEach
    void setUp() {
        sampleCustomer = new CustomerDTO();
        sampleCustomer.setCustomerId("AAAAAAAAIBFBCJEA");
    }

    @Test
    void sendCustomerToKafkaTopic() {
        String expectedTopic = "customer-topic";
        String expectedKey = "12345";
        
        CompletableFuture<SendResult<String, Object>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(eq(expectedTopic), eq(expectedKey), eq(sampleCustomer)))
                .thenReturn(future);
        customerKafkaProducer.sendCustomer(sampleCustomer);

        verify(kafkaTemplate, times(1)).send(eq(expectedTopic), eq(expectedKey), eq(sampleCustomer));
    }
}