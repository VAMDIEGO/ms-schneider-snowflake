package com.ms.schneider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ms.schneider.dto.CustomerDTO;
import com.ms.schneider.repositories.CustomerRepository;
import com.ms.schneider.service.ISnowflakeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SnowflakeService implements ISnowflakeService {

	private final CustomerRepository repository;

	@Override
	public List<CustomerDTO> getCustomers(int page, int size) {
		 return repository.findCustomers( page, size);
	}

	@Override
	public CustomerDTO getCustomerById(String id) {
		return repository.findCustomerById(id);
	}

}
