package com.ms.schneider.service;

import com.ms.schneider.dto.CustomerDTO;

public interface ICustomerKafkaProducer {

    void sendCustomer(CustomerDTO customer);
}
