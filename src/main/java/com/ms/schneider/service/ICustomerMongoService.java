package com.ms.schneider.service;

import java.util.List;

import com.ms.schneider.entity.CustomerMongoData;

public interface ICustomerMongoService {

	List<CustomerMongoData> getAllCustomers();
}
