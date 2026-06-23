package com.ms.schneider.service;

import java.util.List;

import com.ms.schneider.dto.CustomerDTO;

public interface ISnowflakeService {

	List<CustomerDTO> getCustomers(int page, int size);
	
	public CustomerDTO getCustomerById(String id) ;

}
