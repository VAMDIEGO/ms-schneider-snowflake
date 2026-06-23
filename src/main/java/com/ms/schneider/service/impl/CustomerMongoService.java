package com.ms.schneider.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ms.schneider.entity.CustomerMongoData;
import com.ms.schneider.repositories.CustomerMongoRepository;
import com.ms.schneider.service.ICustomerMongoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerMongoService implements ICustomerMongoService {

    private final CustomerMongoRepository repository;

	@Override
	public List<CustomerMongoData> getAllCustomers() {
		  return repository.findAll();	
	}

   
}
