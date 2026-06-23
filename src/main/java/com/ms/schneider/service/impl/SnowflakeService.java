package com.ms.schneider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ms.schneider.dto.CustomerDTO;
import com.ms.schneider.repositories.CustomerRepository;
import com.ms.schneider.service.ICustomerKafkaProducer;
import com.ms.schneider.service.ISnowflakeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SnowflakeService implements ISnowflakeService {

	private final CustomerRepository repository;

	private final ICustomerKafkaProducer iCustomerKafkaProducer;

	@Override
	public List<CustomerDTO> getCustomers(int page, int size) {
		return repository.findCustomers(page, size);
	}

	@Override
	public CustomerDTO getCustomerById(String id) {
		CustomerDTO customer = repository.findCustomerById(id);

		if (customer != null) {
			iCustomerKafkaProducer.sendCustomer(customer);
		}
		return customer;
	}

}
