package com.ms.schneider.service.impl;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ms.schneider.dto.CustomerDTO;
import com.ms.schneider.repositories.CustomerRepository;
import com.ms.schneider.service.ICustomerKafkaProducer;
import com.ms.schneider.service.ISnowflakeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerKafkaProducer implements ICustomerKafkaProducer {

	 private final KafkaTemplate<String, Object> kafkaTemplate;
	 
	@Override
	public void sendCustomer(CustomerDTO customer) {
		kafkaTemplate.send("customer-topic", customer.getCustomerId(), customer);
		 System.out.println("Cliente enbviado a Kafka: " + customer.toString());
		
	}

}
